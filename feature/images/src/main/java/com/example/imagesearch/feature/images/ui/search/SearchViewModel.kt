package com.example.imagesearch.feature.images.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearch.core.data.model.ImageItem
import com.example.imagesearch.core.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(
        val images: List<ImageItem>,
        val downloadingIds: Set<Int> = emptySet(),
        val downloadMessage: String? = null
    ) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun searchImages(query: String, page: Int = 1) {
        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            
            imageRepository.searchImages(query, page)
                .onSuccess { images ->
                    _uiState.value = SearchUiState.Success(images)
                }
                .onFailure { exception ->
                    _uiState.value = SearchUiState.Error(
                        exception.message ?: "搜尋失敗"
                    )
                }
        }
    }

    fun downloadImage(imageItem: ImageItem) {
        val currentState = _uiState.value
        if (currentState is SearchUiState.Success) {
            // 添加到下載中的列表
            _uiState.value = currentState.copy(
                downloadingIds = currentState.downloadingIds + imageItem.id
            )
            
            viewModelScope.launch {
                imageRepository.downloadImage(imageItem)
                    .onSuccess {
                        // 從下載中列表移除，並更新圖片狀態
                        val updatedImages = currentState.images.map { image ->
                            if (image.id == imageItem.id) {
                                image.copy(isDownloaded = true)
                            } else {
                                image
                            }
                        }
                        
                        _uiState.value = SearchUiState.Success(
                            images = updatedImages,
                            downloadingIds = currentState.downloadingIds - imageItem.id,
                            downloadMessage = "下載成功"
                        )
                    }
                    .onFailure { exception ->
                        // 從下載中列表移除
                        _uiState.value = currentState.copy(
                            downloadingIds = currentState.downloadingIds - imageItem.id,
                            downloadMessage = "下載失敗: ${exception.message}"
                        )
                    }
            }
        }
    }
}