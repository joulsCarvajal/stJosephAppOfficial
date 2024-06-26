package com.alphazetakapp.stjosephappofficial.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.alphazetakapp.stjosephappofficial.navigation.AppNavigation
import com.alphazetakapp.stjosephappofficial.ui.theme.StjosephappofficialTheme
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Este sería el tema de arranque
        enableEdgeToEdge()
        setContent {
            StjosephappofficialTheme {
                AppNavigation()
            }
        }
    }
}