plugins {
    alias(libs.plugins.imagesearch.android.library)
    alias(libs.plugins.imagesearch.android.hilt)
}

android {
    namespace = "com.example.imagesearch.core.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.files)

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.bundles.testing)
}