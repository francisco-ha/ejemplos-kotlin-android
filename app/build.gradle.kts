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
        jvmTarget = "1.8"
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
        jvmToolchain { 8 }
    }

}

dependencies {
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // Jetpack Compose (Configuración correcta usando BOM)
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
