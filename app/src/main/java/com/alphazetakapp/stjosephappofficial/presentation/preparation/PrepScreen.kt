package com.alphazetakapp.stjosephappofficial.presentation.preparation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.presentation.navigation.Routes
import com.alphazetakapp.stjosephappofficial.presentation.navigation.Screen
import com.alphazetakapp.stjosephappofficial.ui.BannerApp
import com.google.android.gms.ads.MobileAds

@Composable
fun PrepScreen(navController: NavController, viewModel: PrepViewModel = hiltViewModel()) {
    val backColor = colorResource(id = R.color.backgroundColorApp)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        MobileAds.initialize(context) {}
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backColor)
    ) {
        // Banner posicionado absolutamente en la parte superior
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
        ) {
            BannerApp()
        }

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "❤️ TREINTENA A  SAN JOSÉ ❤️\n" +
                        "\uD83D\uDC63PASOS A SEGUIR\uD83D\uDC63\n" +
                        "\n" +
                        "1.- Rosario a San José\n" +
                        "(te invitamos a escuchar el audio para ayudarte a entender  como se reza)\n" +
                        "2- Letanías a San José\n" +
                        "3.- Meditación\n" +
                        "4.- Oración final.\n" +
                        "\n" +
                        "NOTA: Cada paso, va llevar su parte escrita y su audio\n" +
                        "Por si quieres escucharla o leerla o ambas.",
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = { navController.navigate(Screen.Days.route) },
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF2A71B),
                    contentColor = Color.Black
                )
            ) {
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Next")
            }
        }
    }
}