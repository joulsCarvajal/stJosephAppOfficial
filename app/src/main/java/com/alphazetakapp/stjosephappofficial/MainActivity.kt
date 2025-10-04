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

        // Manejar navegación desde notificaciones
        handleNotificationNavigation(intent)

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

    override fun onNewIntent(intent: android.content.Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleNotificationNavigation(it) }
    }

    private fun handleNotificationNavigation(intent: android.content.Intent) {
        val navigateToDay = intent.getIntExtra("navigate_to_day", -1)
        if (navigateToDay > 0) {
            // Aquí puedes agregar lógica para navegar directamente a un día específico
            // Por ejemplo, usando un ViewModel compartido o pasando parámetros
            println("Navegar al día: $navigateToDay")
        }
    }
}