plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.group17.inventoryease"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.group17.inventoryease"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.securityCrypto)
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)
    implementation(libs.loggingInterceptor)
    implementation(libs.javaJWT)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}