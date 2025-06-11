plugins {
    alias(libs.plugins.imagesearch.android.library)
    alias(libs.plugins.imagesearch.android.hilt)
    alias(libs.plugins.imagesearch.android.room)
}

android {
    namespace = "com.example.imagesearch.core.database"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.bundles.testing)
}