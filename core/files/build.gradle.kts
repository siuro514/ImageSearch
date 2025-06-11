plugins {
    alias(libs.plugins.imagesearch.android.library)
    alias(libs.plugins.imagesearch.android.hilt)
}

android {
    namespace = "com.example.imagesearch.core.files"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.okhttp.core)

    testImplementation(libs.bundles.testing)
}