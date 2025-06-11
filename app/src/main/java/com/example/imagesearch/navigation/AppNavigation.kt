package com.example.imagesearch.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imagesearch.feature.images.navigation.imagesNavigation
import com.example.imagesearch.ui.main.MainScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    var canNavigateBack by remember { mutableStateOf(false) }
    
    // Android 15 Predictive Back Gesture Support
    BackHandler(enabled = canNavigateBack) {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
        }
    }
    
    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ) {
        composable("main") {
            canNavigateBack = false
            MainScreen(
                onNavigateToSearch = {
                    navController.navigate("images/search")
                    canNavigateBack = true
                },
                onNavigateToDownloaded = {
                    navController.navigate("images/downloaded")
                    canNavigateBack = true
                }
            )
        }
        
        // Images feature navigation
        imagesNavigation(
            navController = navController,
            onNavigateBack = {
                canNavigateBack = navController.previousBackStackEntry != null
                navController.popBackStack()
            }
        )
    }
}