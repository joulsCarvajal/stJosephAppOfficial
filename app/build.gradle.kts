plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.alphazetakapp.stjosephappofficial"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alphazetakapp.stjosephappofficial"
        minSdk = 22
        targetSdk = 36
        versionCode = 14
        versionName = "1.0.14"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "environment"

    productFlavors{
        create("dev"){
            dimension = "environment"
            applicationIdSuffix = ".dev"
            // Configura el ID de la app de AdMob para desarrollo
            manifestPlaceholders["adMobAppId"] = "ca-app-pub-3940256099942544~3347511713"  // ID de prueba de AdMob
            buildConfigField("String", "BANNER_AD_UNIT_ID", "\"ca-app-pub-3940256099942544/9214589741\"")
        }

        create("prod") {
            dimension = "environment"
            manifestPlaceholders["adMobAppId"] = "ca-app-pub-4246199849789587~9714608451"  // APP_ID_PROD
            buildConfigField("String", "BANNER_AD_UNIT_ID", "\"ca-app-pub-4246199849789587/5444152964\"")  // BANNER_AD_PROD
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.2.1")
    implementation ("androidx.navigation:navigation-compose:2.5.1")

    // optional - RxJava2 support
    implementation("androidx.datastore:datastore-preferences-rxjava2:1.0.0")
    // optional - RxJava3 support
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.0.0")

    //Ads tipo banner
    implementation ("com.google.android.gms:play-services-ads:22.5.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    // Para usar Hilt con Navigation Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Si usas WorkManager con Hilt
    implementation("androidx.hilt:hilt-work:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

}

kapt {
    correctErrorTypes = true
}