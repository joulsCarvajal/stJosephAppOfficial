package com.alphazetakapp.stjosephappofficial.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.unit.dp

@Composable
fun ResponsiveLayout(
    content: @Composable () -> Unit,
    tabletContent: @Composable (() -> Unit)? = null
) {
    BoxWithConstraints {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        
        when {
            // Para tablets y pantallas grandes
            screenWidth >= 600.dp && tabletContent != null -> {
                tabletContent()
            }
            // Para teléfonos y pantallas más pequeñas
            else -> {
                content()
            }
        }
    }
} 