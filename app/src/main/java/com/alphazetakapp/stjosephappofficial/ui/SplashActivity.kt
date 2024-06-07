package com.alphazetakapp.stjosephappofficial.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alphazetakapp.stjosephappofficial.ui.theme.StjosephappofficialTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StjosephappofficialTheme {
                SplashScreenStJosephApp()
            }
        }
    }
}