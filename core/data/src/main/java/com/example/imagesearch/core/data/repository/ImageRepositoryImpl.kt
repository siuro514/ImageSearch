package com.example.imagesearch.core.data.repository

import com.example.imagesearch.core.data.mapper.toDownloadedImageEntity
import com.example.imagesearch.core.data.mapper.toImageItem
import com.example.imagesearch.core.data.model.ImageItem
import com.example.imagesearch.core.database.dao.ImageDao
import com.example.imagesearch.core.files.FileManager
import com.example.imagesearch.core.network.api.PixabayApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl @Inject constructor(
    private val pixabayApi: PixabayApi,
    private val imageDao: ImageDao,
    private val fileManager: FileManager
) : ImageRepository {

    override suspend fun searchImages(query: String, page: Int): Result<List<ImageItem>> {
        return try {
            val response = pixabayApi.searchImages(query = query, page = page)
            val downloadedIds = imageDao.getAllDownloadedImageIds()
            
            val imageItems = response.hits.map { pixabayImage ->
                val imageItem = pixabayImage.toImageItem()
                if (downloadedIds.contains(imageItem.id)) {
                    val downloadedEntity = imageDao.getDownloadedImageById(imageItem.id)
                    downloadedEntity?.toImageItem() ?: imageItem.copy(isDownloaded = true)
                } else {
                    imageItem
                }
            }
            
            Result.success(imageItems)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun downloadImage(imageItem: ImageItem): Result<String> {
        return try {
            val localPath = fileManager.downloadImage(
                url = imageItem.largeImageURL,
                fileName = "image_${imageItem.id}.jpg"
            )
            
            val downloadedEntity = imageItem.toDownloadedImageEntity(localPath)
            imageDao.insertDownloadedImage(downloadedEntity)
            
            Result.success(localPath)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getDownloadedImages(): Flow<List<ImageItem>> {
        return imageDao.getAllDownloadedImages().map { entities ->
            entities.map { it.toImageItem() }
        }
    }

    override suspend fun deleteDownloadedImage(imageId: Int): Result<Unit> {
        return try {
            val downloadedImage = imageDao.getDownloadedImageById(imageId)
            if (downloadedImage != null) {
                fileManager.deleteFile(downloadedImage.localPath)
                imageDao.deleteDownloadedImage(imageId)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isImageDownloaded(imageId: Int): Boolean {
        return imageDao.getDownloadedImageById(imageId) != null
    }
}