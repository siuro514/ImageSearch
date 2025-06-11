import com.android.build.gradle.LibraryExtension
import com.example.imagesearch.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                defaultConfig.consumerProguardFiles("consumer-rules.pro")
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true
            }
        }
    }
}