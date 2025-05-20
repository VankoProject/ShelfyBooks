// Top-level build file where you can add configuration options common to all sub-projects/modules.
val localProperties = java.util.Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        file.inputStream().use { load(it) }
    }
}

val nytApiKey = localProperties.getProperty("NYT_API_KEY") ?: ""
extra.set("NYT_API_KEY", nytApiKey)

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.google.services) apply false

    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
}