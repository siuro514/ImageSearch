package com.example.imagesearch.core.database.dao

import androidx.room.*
import com.example.imagesearch.core.database.entity.DownloadedImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    
    @Query("SELECT * FROM downloaded_images ORDER BY downloadTime DESC")
    fun getAllDownloadedImages(): Flow<List<DownloadedImageEntity>>
    
    @Query("SELECT * FROM downloaded_images WHERE id = :imageId")
    suspend fun getDownloadedImageById(imageId: Int): DownloadedImageEntity?
    
    @Query("SELECT id FROM downloaded_images")
    suspend fun getAllDownloadedImageIds(): List<Int>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedImage(image: DownloadedImageEntity)
    
    @Query("DELETE FROM downloaded_images WHERE id = :imageId")
    suspend fun deleteDownloadedImage(imageId: Int)
    
    @Query("DELETE FROM downloaded_images")
    suspend fun deleteAllDownloadedImages()
}