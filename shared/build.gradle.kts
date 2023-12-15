plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildKonfig)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.coroutine.core)
            implementation(libs.ktor.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.content.negotiation)
            api(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.android)
            api(libs.koin.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.ios)
        }
    }
}

buildkonfig {
    packageName = "com.gowittroup.mystiqflix"

    defaultConfigs {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "THE_MOVIE_DB_API_KEY", ((System.getenv("THE_MOVIE_DB_API_KEY") ?: "") as String))
    }
}

android {
    namespace = "com.gowittgroup.mystiqflix"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
