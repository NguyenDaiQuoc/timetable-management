// File: app/build.gradle.kts

// --- KHỐI PLUGINS (Luôn nằm ở đầu file) ---
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") // Cần thiết cho Room Database
}

// --- KHỐI ANDROID (Chứa cấu hình SDK và ứng dụng) ---
android {
    // Thuộc tính quan trọng đã bị thiếu trước đó:
    compileSdk = 34 // Phiên bản SDK để biên dịch

    namespace = "com.example.quanlythoikhoabieu" // Thay bằng package của bạn

    defaultConfig {
        applicationId = "com.example.quanlythoikhoabieu" // Thay bằng package của bạn
        minSdk = 26 // Hỗ trợ Android 7.0 (Nougat) trở lên
        targetSdk = 34 // Phiên bản Android mục tiêu
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

    // Cấu hình Java/Kotlin
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // Nâng cấp lên 17
        targetCompatibility = JavaVersion.VERSION_17 // Nâng cấp lên 17
    }
    kotlinOptions {
        jvmTarget = "17" // Nâng cấp lên 17
    }
}

// --- KHỐI DEPENDENCIES (Thư viện) ---
dependencies {
    // Standard Android UI/Kotlin
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Room Database
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    // ViewModel & LiveData (Jetpack Architecture Components)
    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    // Test dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}