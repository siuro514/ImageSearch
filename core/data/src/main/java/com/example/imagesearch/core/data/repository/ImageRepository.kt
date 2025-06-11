package com.example.imagesearch.core.data.repository

import com.example.imagesearch.core.data.model.ImageItem
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun searchImages(query: String, page: Int = 1): Result<List<ImageItem>>
    suspend fun downloadImage(imageItem: ImageItem): Result<String>
    fun getDownloadedImages(): Flow<List<ImageItem>>
    suspend fun deleteDownloadedImage(imageId: Int): Result<Unit>
    suspend fun isImageDownloaded(imageId: Int): Boolean
}