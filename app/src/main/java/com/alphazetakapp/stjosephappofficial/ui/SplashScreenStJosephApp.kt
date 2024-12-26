package com.alphazetakapp.stjosephappofficial.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alphazetakapp.stjosephappofficial.MainActivity
import com.alphazetakapp.stjosephappofficial.R

@Preview
@Composable
fun SplashScreenStJosephApp(){
    val alpha = remember {
        Animatable(0f)
    }
    val context = LocalContext.current
    val activity = (context as? Activity)

    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f, animationSpec = tween(1300))
        context.startActivity(Intent(context, MainActivity::class.java))
        // Finalizamos el SplashActivity para que no quede en el stack
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
            Image(
                painter = painterResource(id = R.drawable.loguitook3),
                contentDescription = "Escudo",
                modifier = Modifier.size(400.dp).alpha(alpha.value)
            )
        }
    }

}