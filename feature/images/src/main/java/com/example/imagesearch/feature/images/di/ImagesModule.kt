package com.example.imagesearch.feature.images.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ImagesModule {
    // 目前沒有需要提供的依賴項目
    // 如果未來需要特定的依賴注入，可以在這裡添加
}