package com.alphazetakapp.stjosephappofficial

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StJosephApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicializa MobileAds
        MobileAds.initialize(this) {}
    }
}
