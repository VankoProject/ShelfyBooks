plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.kliachenko.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        buildConfigField(
            "String",
            "NYT_API_KEY",
            "\"${rootProject.extra["NYT_API_KEY"]}\""
        )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
    }
}

hilt {
    enableAggregatingTask = false
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.kotlinx.coroutines.core)

    //Hilt, KSP
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Firebase
    api(platform(libs.firebase.bom))
    api(libs.firebase.auth)
    api(libs.play.services.auth)
    api(libs.firebase.analytics)

    //Retrofit
    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.logging.interceptor)

    //Room
    api(libs.room.runtime)
    api(libs.room.ktx)
    ksp(libs.room.compiler)

}