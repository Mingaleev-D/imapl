plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.example.imapl"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "ru.example.imapl"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}

dependencies {
    // --------------------------------------------------------------------------------
    // Core & Lifecycle
    // --------------------------------------------------------------------------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Интеграция ViewModel в Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Поддержка Activity для Compose
    implementation(libs.androidx.activity.compose)
    // SavedState
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    // Утилиты для Splash Screen API (Android 12+)
    implementation(libs.androidx.core.splashscreen)

    // --------------------------------------------------------------------------------
    // UI Layer: Jetpack Compose
    // --------------------------------------------------------------------------------
    // BOM (Bill of Materials) для синхронизации версий Compose библиотек
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    // Поддержка ConstraintLayout в Compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.1")
    // Работа с устаревшими ресурсами (AppCompat) при миграции
    implementation(libs.androidx.appcompat)

    // Тестирование UI (Compose)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
    // debugImplementation(libs.androidx.compose.ui.test.manifest)

    //   --------------------------------------------------------------------------------
    //     testImplementation(libs.junit)
    //     androidTestImplementation(libs.androidx.junit)
    //     androidTestImplementation(libs.androidx.espresso.core)
    //     androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // --------------------------------------------------------------------------------
    // Navigation
    // --------------------------------------------------------------------------------
    // Навигация Compose (включая Type Safety в новых версиях)
    implementation(libs.androidx.navigation.compose)

    // --------------------------------------------------------------------------------
    // Dependency Injection (Hilt)
    // --------------------------------------------------------------------------------
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    // Интеграция Hilt с Navigation Compose
    implementation(libs.androidx.hilt.navigation.compose)
    // Интеграция Hilt с ViewModel
    implementation(libs.androidx.hilt.lifecycle.viewmodel)

    // --------------------------------------------------------------------------------
    // Asynchronous & Concurrency
    // --------------------------------------------------------------------------------
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Интеграция Coroutines с Play Services (Task API)
    implementation(libs.kotlinx.coroutines.play.services)

    // --------------------------------------------------------------------------------
    // Network Layer
    // --------------------------------------------------------------------------------
    // REST клиент
    implementation(libs.retrofit)
    // HTTP клиент и логгирование запросов
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    // Конвертер для Kotlinx Serialization
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    // JSON сериализация
    implementation(libs.kotlinx.serialization.json)

    // --------------------------------------------------------------------------------
    // Image Loading
    // --------------------------------------------------------------------------------
    // Coil 3.x (Compose-first загрузчик изображений)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)




    implementation("com.github.skydoves:cloudy:0.1.2")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
}