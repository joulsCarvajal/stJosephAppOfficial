package com.alphazetakapp.stjosephappofficial.ui.Anuncios

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.alphazetakapp.stjosephappofficial.BuildConfig
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

class AdManager(private val context: Context) {
    init {
        MobileAds.initialize(context)
        Log.d("AdManager", "MobileAds initialized with applicationId: ${context.packageName}")

        if (BuildConfig.DEBUG){
            val testDeviceIds = listOf("TEST-DEVICE-ID")
            val configuration = RequestConfiguration.Builder()
                .setTestDeviceIds(testDeviceIds)
                .build()
            MobileAds.setRequestConfiguration(configuration)
        }
    }

    fun getBannerAdUnitId(): String {
        val adId = if (BuildConfig.DEBUG) {
            AdConstants.BANNER_AD_TEST
        } else {
            AdConstants.BANNER_AD_PROD
        }
        Log.d("AdManager", "Using Ad ID: $adId")
        return adId
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: AdManager? = null

        fun getInstance(context: Context): AdManager {
            return instance ?: synchronized(this) {
                instance ?: AdManager(context).also { instance = it }
            }
        }
    }
}