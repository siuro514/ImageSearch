package com.example.imagesearch.feature.images.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.imagesearch.feature.images.ui.downloaded.DownloadedScreen
import com.example.imagesearch.feature.images.ui.search.SearchScreen

fun NavGraphBuilder.imagesNavigation(
    navController: NavController,
    onNavigateBack: () -> Unit
) {
    composable("images/search") {
        SearchScreen(
            onNavigateBack = onNavigateBack
        )
    }
    
    composable("images/downloaded") {
        DownloadedScreen(
            onNavigateBack = onNavigateBack
        )
    }
}