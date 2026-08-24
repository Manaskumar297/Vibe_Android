plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)


}

android {
    namespace = "com.manas.vibe"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.manas.vibe"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes{
        debug {

            buildConfigField(
                "String",
                "BASE_URl",
                project.findProperty("DEV_BASE_URL")as String? ?: ""
            )
            buildConfigField(
                "String",
                "SOCKET_URL",
                project.findProperty("SOCKET_URL") as String ? ?: ""
            )

        }
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
            buildConfigField(
                "String",
                "BASE_URl",
                project.findProperty("PROD_BASE_URL")as String? ?: ""
            )
            buildConfigField(
                "String",
                "SOCKET_URL",
                project.findProperty("SOCKET_URL") as String ? ?: ""
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
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)


    implementation(libs.androidx.navigation.compose)

    //for phone number length validation according to the country code
    implementation(libs.libphonenumber)


// Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


////Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
}