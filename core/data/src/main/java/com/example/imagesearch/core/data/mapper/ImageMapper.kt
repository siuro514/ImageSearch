package com.example.imagesearch.core.data.mapper

import com.example.imagesearch.core.data.model.ImageItem
import com.example.imagesearch.core.database.entity.DownloadedImageEntity
import com.example.imagesearch.core.network.model.PixabayImage

fun PixabayImage.toImageItem(): ImageItem {
    return ImageItem(
        id = id,
        pageURL = pageURL,
        type = type,
        tags = tags,
        previewURL = previewURL,
        webformatURL = webformatURL,
        largeImageURL = largeImageURL,
        views = views,
        downloads = downloads,
        likes = likes,
        user = user
    )
}

fun DownloadedImageEntity.toImageItem(): ImageItem {
    return ImageItem(
        id = id,
        pageURL = pageURL,
        type = type,
        tags = tags,
        previewURL = previewURL,
        webformatURL = webformatURL,
        largeImageURL = largeImageURL,
        views = views,
        downloads = downloads,
        likes = likes,
        user = user,
        isDownloaded = true,
        localPath = localPath
    )
}

fun ImageItem.toDownloadedImageEntity(localPath: String): DownloadedImageEntity {
    return DownloadedImageEntity(
        id = id,
        pageURL = pageURL,
        type = type,
        tags = tags,
        previewURL = previewURL,
        webformatURL = webformatURL,
        largeImageURL = largeImageURL,
        views = views,
        downloads = downloads,
        likes = likes,
        user = user,
        localPath = localPath,
        downloadTime = System.currentTimeMillis()
    )
}