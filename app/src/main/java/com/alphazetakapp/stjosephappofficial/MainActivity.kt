package com.alphazetakapp.stjosephappofficial

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.alphazetakapp.stjosephappofficial.presentation.navigation.AppNavigation
import com.alphazetakapp.stjosephappofficial.ui.theme.StjosephappofficialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Actualización del manejo de insets del sistema
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //Este sería el tema de arranque
        enableEdgeToEdge()
        setContent {
            StjosephappofficialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    AppNavigation(
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}