plugins {
    alias(libs.plugins.imagesearch.android.library)
    alias(libs.plugins.imagesearch.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.imagesearch.core.network"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.networking)

    testImplementation(libs.bundles.testing)
}