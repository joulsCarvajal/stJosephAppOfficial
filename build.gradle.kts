// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
    id("com.android.library") version "8.1.4" apply false
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50") // Última versión estable
    }
}