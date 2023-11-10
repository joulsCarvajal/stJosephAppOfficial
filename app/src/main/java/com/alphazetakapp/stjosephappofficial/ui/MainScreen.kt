package com.alphazetakapp.stjosephappofficial.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.navigation.Routes

@Composable
fun MainScreen(navController: NavController, context: Context) {
    val backColor = colorResource(id = R.color.backgroundColorApp)
    val mainText = stringResource(id = R.string.main_screen_text)
    val title = stringResource(id = R.string.title_main_screen)

    val textWithStyles = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.SansSerif
            )
        ) {
            append(mainText)
        }
    }

    val textWithStylesTitle = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )
        ) {
            append(title)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backColor)
                .padding(4.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = textWithStylesTitle,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backColor)
                .padding(4.dp)
                .verticalScroll(rememberScrollState())
        ){
            Text(
                text = textWithStyles,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backColor)
                .padding(4.dp),
            contentAlignment = Alignment.BottomEnd
        ){
            Row {
                Button(
                    onClick = { navController.navigate(Routes.PrepMeditation.routes) },
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFF2A71B),
                        contentColor = Color.Black
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Next")
                }
            }
        }
    }
}