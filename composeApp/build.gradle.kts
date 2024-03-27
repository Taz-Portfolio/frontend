import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.material)
            api(compose.foundation)
            api(compose.animation)
            implementation(compose.ui)
            implementation(compose.components.resources)

            api(libs.precompose)
            implementation(libs.koin)
            implementation(libs.koin.compose)
        }

        val wasmJsMain by getting {
            dependencies {
            }
        }
    }
}

compose.experimental {
    web.application {}
}
