plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") // 1. Cambiado a la sintaxis moderna de Kotlin
}

android {
    compileSdk = 34
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.cursokotlin.retrofitkotlinexample"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // 2. Corregido: En Kotlin DSL se usa 'getByName("debug")'
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true // 3. Corregido: Se usa 'isDebuggable' en lugar de 'debuggable'
        }
        create("debug2") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".anexo"
        }
        // 4. Corregido: En Kotlin DSL se usa 'getByName("release")'
        getByName("release") {
            isMinifyEnabled = true // 5. Corregido: Se usa 'isMinifyEnabled'
            isShrinkResources = true // 6. Corregido: Se usa 'isShrinkResources'
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("version")

    productFlavors {
        create("freeVersion") {
            dimension = "version"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
            buildConfigField("String", "URL", "\"develop.com\"")
            buildConfigField("boolean", "isAllow", "false")
        }
        create("premiumVersion") {
            dimension = "version"
            applicationIdSuffix = ".premium"
            versionNameSuffix = "-premium"
            buildConfigField("String", "URL", "\"producion.com\"")
            buildConfigField("boolean", "isAllow", "true")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions{
        kotlinCompilerExtensionVersion = "1.4.3"

    }
    kotlin{
        jvmToolchain { 17 }
    }

}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)

    // Picasso
    implementation(libs.picasso)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Shimmer
    implementation(libs.shimmer)

    // Jetpack Compose (Configuración correcta usando BOM)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
