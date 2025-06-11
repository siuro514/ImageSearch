package com.example.imagesearch.core.network.api

import com.example.imagesearch.core.network.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    
    @GET("api/")
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("image_type") imageType: String = "photo",
        @Query("orientation") orientation: String = "all",
        @Query("category") category: String = "all",
        @Query("min_width") minWidth: Int = 0,
        @Query("min_height") minHeight: Int = 0,
        @Query("safesearch") safeSearch: String = "true",
        @Query("order") order: String = "popular",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): PixabayResponse

    companion object {
        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = "YOUR_API_KEY_HERE" // 請替換為你的 Pixabay API Key
    }
}