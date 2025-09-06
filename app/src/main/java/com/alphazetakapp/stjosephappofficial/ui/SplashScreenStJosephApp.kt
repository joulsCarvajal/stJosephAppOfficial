package com.alphazetakapp.stjosephappofficial.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alphazetakapp.stjosephappofficial.MainActivity
import com.alphazetakapp.stjosephappofficial.R
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreenStJosephApp(){
    val context = LocalContext.current
    val activity = (context as? Activity)
    
    // Animación de fade para el logo
    val alpha = remember {
        Animatable(0f)
    }
    
    // Animación de escala pulsante
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    

    LaunchedEffect(key1 = true) {
        // Iniciar la animación de fade-in
        alpha.animateTo(1f, animationSpec = tween(1500))
        // Pausa para mostrar la animación
        delay(2500)
        context.startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF8B4513))
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            // Mostrar el logo original con animaciones nativas
            Image(
                painter = painterResource(id = R.drawable.loguitook3),
                contentDescription = "Escudo de San José",
                modifier = Modifier
                    .size(400.dp)
                    .alpha(alpha.value)
                    .scale(scale)
            )
            
            // Texto debajo del logo
            Text(
                text = "San José ruega por nosotros",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .alpha(alpha.value)
            )
        }
    }
}