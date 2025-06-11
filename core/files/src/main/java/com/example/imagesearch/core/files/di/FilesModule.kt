package com.example.imagesearch.core.files.di

import com.example.imagesearch.core.files.FileManager
import com.example.imagesearch.core.files.FileManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FilesModule {

    @Binds
    @Singleton
    abstract fun bindFileManager(
        fileManagerImpl: FileManagerImpl
    ): FileManager
}
