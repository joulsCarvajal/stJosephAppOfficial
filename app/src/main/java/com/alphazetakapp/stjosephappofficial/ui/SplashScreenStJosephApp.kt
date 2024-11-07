package com.alphazetakapp.stjosephappofficial.ui

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
    LaunchedEffect(key1 = true, block = {
        alpha.animateTo(1f, animationSpec = tween(1300))
        /*delay(1000)*/
        context.startActivity(Intent(context, MainActivity::class.java))
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4C2709))
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