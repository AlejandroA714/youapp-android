plugins {
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.application)
}

android {
    compileSdk = 36
    namespace = "sv.com.youapp"

    buildFeatures {
        compose = true
    }

    defaultConfig {
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
        applicationId = "sv.com.youapp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    // Compose
    implementation(libs.androidx.compose.ui)
    // Material (Xml, Compose)
    implementation(libs.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material3.icons)
    implementation(libs.androidx.material3.icons.extended)
    // Preview
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.browser)
    // Debug Preview
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.activity.compose)
    // Opcional
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.hilt.navigation.compose)
    // Google
    implementation(libs.bundles.credentials.google)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // RetroFit
    implementation(platform(libs.retrofit.bom))
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
}