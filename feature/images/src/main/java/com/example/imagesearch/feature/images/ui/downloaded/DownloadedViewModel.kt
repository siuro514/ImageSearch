package com.example.imagesearch.feature.images.ui.downloaded

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

data class DownloadedUiState(
    val images: List<ImageItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class DownloadedViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DownloadedUiState(isLoading = true))
    val uiState: StateFlow<DownloadedUiState> = _uiState.asStateFlow()

    init {
        loadDownloadedImages()
    }

    private fun loadDownloadedImages() {
        viewModelScope.launch {
            imageRepository.getDownloadedImages().collect { images ->
                _uiState.value = _uiState.value.copy(
                    images = images,
                    isLoading = false
                )
            }
        }
    }

    fun deleteImage(imageId: Int) {
        viewModelScope.launch {
            imageRepository.deleteDownloadedImage(imageId)
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "刪除失敗: ${exception.message}"
                    )
                }
        }
    }

    fun deleteAllImages() {
        viewModelScope.launch {
            val currentImages = _uiState.value.images
            currentImages.forEach { image ->
                imageRepository.deleteDownloadedImage(image.id)
            }
        }
    }
}