package com.alphazetakapp.stjosephappofficial.ui

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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

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
                }
            }
        )
    }
}