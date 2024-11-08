package com.alphazetakapp.stjosephappofficial.presentation.meditation.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import com.alphazetakapp.stjosephappofficial.presentation.common.ErrorMessage
import com.alphazetakapp.stjosephappofficial.presentation.common.LoadingIndicator
import com.alphazetakapp.stjosephappofficial.presentation.navigation.Screen

@Composable
fun DaysScreen(
    navController: NavController,
    viewModel: DaysViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedDay by viewModel.selectedDay.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC2932A))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is DaysUiState.Loading -> LoadingIndicator()
            is DaysUiState.Error -> ErrorMessage((uiState as DaysUiState.Error).message)
            is DaysUiState.Success -> {
                MeditationDaysList(
                    meditations = (uiState as DaysUiState.Success).meditations,
                    selectedDay = selectedDay,
                    onDaySelected = { meditation ->
                        viewModel.onDaySelected(meditation.dayNum)
                        navController.navigate(
                            Screen.MeditationNav.createRoute(
                                dayNum = meditation.dayNum,
                                day = meditation.day,
                                dailyRecord = meditation.dailyRecord ?: 0
                            )
                        )
                    }
                )
            }
        }
    }
}


@Composable
private fun MeditationDaysList(
    meditations: List<Meditation>,
    selectedDay: Int?,
    onDaySelected: (Meditation) -> Unit
) {
    LazyColumn (
        contentPadding = PaddingValues(bottom = 40.dp)
    ) {
        items(meditations) { meditation ->
            MeditationDayCard(
                meditation = meditation,
                isSelected = selectedDay == meditation.dayNum,
                onClick = { onDaySelected(meditation) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun MeditationDayCard(
    meditation: Meditation,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backColor = colorResource(id = R.color.backgroundColorApp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        border = BorderStroke(2.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.Gray else backColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = meditation.imageResId),
                contentDescription = "Imagen del día ${meditation.dayNum}",
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 8.dp),
                alignment = Alignment.CenterStart
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = meditation.day,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = meditation.meditationDay,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 18.dp)
                        .padding(bottom = 8.dp),
                    color = Color.White
                )
            }
        }
    }
}