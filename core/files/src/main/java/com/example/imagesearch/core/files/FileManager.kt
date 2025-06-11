package com.example.imagesearch.core.files

interface FileManager {
    suspend fun downloadImage(url: String, fileName: String): String
    suspend fun deleteFile(filePath: String): Boolean
    suspend fun getFileSize(filePath: String): Long
    suspend fun fileExists(filePath: String): Boolean
    fun getDownloadDirectory(): String
}