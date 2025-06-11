package com.example.imagesearch.core.database.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.imagesearch.core.database.dao.ImageDao
import com.example.imagesearch.core.database.entity.DownloadedImageEntity

@Database(
    entities = [DownloadedImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

    companion object {
        const val DATABASE_NAME = "image_search_database"
    }
}