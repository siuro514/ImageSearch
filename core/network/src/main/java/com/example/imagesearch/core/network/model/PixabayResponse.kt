package com.example.imagesearch.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PixabayResponse(
    @SerialName("total")
    val total: Int,
    @SerialName("totalHits")
    val totalHits: Int,
    @SerialName("hits")
    val hits: List<PixabayImage>
)

@Serializable
data class PixabayImage(
    @SerialName("id")
    val id: Int,
    @SerialName("pageURL")
    val pageURL: String,
    @SerialName("type")
    val type: String,
    @SerialName("tags")
    val tags: String,
    @SerialName("previewURL")
    val previewURL: String,
    @SerialName("webformatURL")
    val webformatURL: String,
    @SerialName("largeImageURL")
    val largeImageURL: String,
    @SerialName("views")
    val views: Int,
    @SerialName("downloads")
    val downloads: Int,
    @SerialName("likes")
    val likes: Int,
    @SerialName("user")
    val user: String
)