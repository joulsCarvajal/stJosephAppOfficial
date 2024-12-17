package com.alphazetakapp.stjosephappofficial.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.alphazetakapp.stjosephappofficial.ui.Anuncios.AdManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun BannerApp(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val adManager = AdManager.getInstance(context)

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(2.dp))
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { ctx ->
                AdView(ctx).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = adManager.getBannerAdUnitId()
                    loadAd(AdRequest.Builder().build())

                    adListener = object : AdListener() {
                        override fun onAdFailedToLoad(error: LoadAdError) {
                            Log.e("AdsDebug", "Ad failed to load: ${error.message}")
                            Log.e("AdsDebug", "Error code: ${error.code}")
                            Log.e("AdsDebug", "Error domain: ${error.domain}")
                        }

                        override fun onAdLoaded() {
                            Log.d("AdsDebug", "Ad loaded successfully")
                        }
                    }
                }
            }
        )
    }
}