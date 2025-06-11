package com.example.imagesearch.core.files

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient
) : FileManager {

    private val downloadDirectory by lazy {
        // Android 15 scoped storage support
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM -> {
                // Use scoped storage for Android 15+
                File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ImageSearch")
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                // Use scoped storage for Android 10+
                File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ImageSearch")
            }
            else -> {
                // Legacy external storage
                File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "ImageSearch"
                )
            }
        }.apply {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    override suspend fun downloadImage(url: String, fileName: String): String {
        return withContext(Dispatchers.IO) {
            // Check permissions for Android 15
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                checkAndroidFifteenPermissions()
            }
            
            val request = Request.Builder()
                .url(url)
                .build()

            val response = okHttpClient.newCall(request).execute()
            
            if (!response.isSuccessful) {
                throw Exception("Failed to download image: ${response.code}")
            }

            val file = File(downloadDirectory, fileName)
            val inputStream = response.body?.byteStream()
                ?: throw Exception("Response body is null")

            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }

            file.absolutePath
        }
    }

    override suspend fun deleteFile(filePath: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val file = File(filePath)
                file.delete()
            } catch (e: Exception) {
                false
            }
        }
    }

    override suspend fun getFileSize(filePath: String): Long {
        return withContext(Dispatchers.IO) {
            try {
                File(filePath).length()
            } catch (e: Exception) {
                0L
            }
        }
    }

    override suspend fun fileExists(filePath: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                File(filePath).exists()
            } catch (e: Exception) {
                false
            }
        }
    }

    override fun getDownloadDirectory(): String {
        return downloadDirectory.absolutePath
    }

    private fun checkAndroidFifteenPermissions() {
        val hasPermission = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED
            }
            else -> true // Scoped storage doesn't require explicit permissions
        }
        
        if (!hasPermission) {
            throw SecurityException("Storage permission not granted")
        }
    }
}