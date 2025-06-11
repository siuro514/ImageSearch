package com.example.imagesearch.core.network.interceptor

import com.example.imagesearch.core.network.api.PixabayApi
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("key", PixabayApi.API_KEY)
            .build()
        
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        
        return chain.proceed(newRequest)
    }
}