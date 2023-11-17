package com.alphazetakapp.stjosephappofficial.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.navigation.AppNavigation
import com.alphazetakapp.stjosephappofficial.ui.theme.StjosephappofficialTheme
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Este sería el tema de arranque
        setTheme(R.style.Theme_App_Starting)

        setContent {
            StjosephappofficialTheme {
                AppNavigation()
            }
        }
    }
}