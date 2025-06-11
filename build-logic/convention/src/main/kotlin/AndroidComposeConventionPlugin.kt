import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            val extension = extensions.getByName("android") as CommonExtension<*, *, *, *, *>
            
            extension.apply {
                buildFeatures {
                    compose = true
                }
                compileOptions {
                    isCoreLibraryDesugaringEnabled = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.findVersion("composeCompiler").get().toString()
                }
            }

            dependencies {
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(bom))
                add("androidTestImplementation", platform(bom))
                
                // Compose Libraries
                add("implementation", libs.findBundle("compose").get())
                add("implementation", libs.findLibrary("androidx-navigation-compose").get())
                add("debugImplementation", libs.findBundle("compose-debug").get())
                add("androidTestImplementation", libs.findLibrary("androidx-ui-test-junit4").get())
            }
        }
    }
}