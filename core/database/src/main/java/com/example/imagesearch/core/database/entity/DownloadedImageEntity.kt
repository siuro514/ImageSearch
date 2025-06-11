package com.example.imagesearch.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_images")
data class DownloadedImageEntity(
    @PrimaryKey
    val id: Int,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val webformatURL: String,
    val largeImageURL: String,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val user: String,
    val localPath: String,
    val downloadTime: Long
)