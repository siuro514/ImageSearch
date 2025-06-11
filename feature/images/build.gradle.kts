plugins {
    alias(libs.plugins.imagesearch.android.library)
    alias(libs.plugins.imagesearch.android.compose)
    alias(libs.plugins.imagesearch.android.hilt)
}

android {
    namespace = "com.example.imagesearch.feature.images"
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)

    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}