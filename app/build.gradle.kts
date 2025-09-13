plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    compileSdk = 36
    ndkVersion = "28.2.13676358"
    namespace = "com.sv.youapp.app"

    buildFeatures {
        compose = true
    }

    defaultConfig {
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
        applicationId = "com.sv.youapp.app"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["appAuthRedirectScheme"] = "com.sv.youapp"
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
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

kotlin{
    jvmToolchain(17)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    // Compose
    implementation(libs.androidx.compose.ui)
    // Material (Xml, Compose)
    implementation(libs.material)
    implementation(libs.androidx.compose.material3)
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
    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}