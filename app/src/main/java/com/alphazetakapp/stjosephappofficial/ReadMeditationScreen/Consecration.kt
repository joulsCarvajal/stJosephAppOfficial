package com.alphazetakapp.stjosephappofficial.ReadMeditationScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.ui.BannerApp
import com.google.android.gms.ads.MobileAds

@Composable
fun TheNewScreen() {
    val backColor = colorResource(id = R.color.backgroundColorApp)
    val title = stringResource(id = R.string.question_mining)
    val meditConsecretion = stringResource(id = R.string.consecration)
    var context = LocalContext.current

    MobileAds.initialize(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backColor)
            .padding(18.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BannerApp()
        Spacer(modifier = Modifier.size(8.dp))
        ExpandableMining(mining = title)
        Spacer(modifier = Modifier.size(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = meditConsecretion, color = Color.White)
        }

    }
}


@Composable
fun ExpandableMining(mining: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(mining) }

    val icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

    val heightModifier =
        Modifier
            .clickable { isExpanded = !isExpanded }
            .animateContentSize()
            .then(
                if (isExpanded) Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp) // Añade padding horizontal para mantener el ancho en pantalla completa
                else Modifier
                    .height(50.dp)
            )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(8.dp)
            .then(heightModifier),
        elevation = CardDefaults.cardElevation(),
        shape = CardDefaults.elevatedShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Expand/Collapse",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = if (isExpanded) text else text.take(33), // Mostrar solo una parte del texto cuando está contraído
                    color = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(end = 8.dp)
                        .weight(1f)
                )
            }
        }
    }
}

