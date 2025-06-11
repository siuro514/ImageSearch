package com.example.imagesearch.core.data.model

data class ImageItem(
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
    val isDownloaded: Boolean = false,
    val localPath: String? = null
)