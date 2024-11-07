package com.alphazetakapp.stjosephappofficial

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.DisposableEffect
import com.alphazetakapp.stjosephappofficial.presentation.navigation.AppNavigation
import com.alphazetakapp.stjosephappofficial.ui.theme.StjosephappofficialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Este sería el tema de arranque
        enableEdgeToEdge()
        setContent {
            StjosephappofficialTheme {
                DisposableEffect(Unit) {
                    val originalOrientation = requestedOrientation
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

                    onDispose {
                        // Esto es opcional, pero permite volver a la orientación original si es necesario
                        requestedOrientation = originalOrientation
                    }
                }
                AppNavigation()
            }
        }
    }
}