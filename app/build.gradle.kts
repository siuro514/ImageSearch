plugins {
    alias(libs.plugins.imagesearch.android.application)
    alias(libs.plugins.imagesearch.android.compose)
    alias(libs.plugins.imagesearch.android.hilt)
}

android {
    namespace = "com.example.imagesearch"
    
    defaultConfig {
        applicationId = "com.example.imagesearch"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
        
        // Android 15 specific configurations
        buildConfigField("int", "TARGET_SDK", "35")
        buildConfigField("String", "BUILD_TYPE", "\"${buildType.name}\"")
    }
    
    // Android 15 Edge-to-edge support
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.feature.images)
    implementation(projects.core.data)
    implementation(projects.core.network)
    implementation(projects.core.files)
    implementation(projects.core.database)

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)
    implementation(libs.androidx.activity.compose)

    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}
