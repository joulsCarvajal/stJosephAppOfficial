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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.alphazetakapp.stjosephappofficial.presentation.common.ResponsiveLayout

@Composable
fun DaysScreen(
    navController: NavController,
    viewModel: DaysViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedDay by viewModel.selectedDay.collectAsState()
    var showResetDialog by remember { mutableStateOf(false) }

    ResponsiveLayout(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFC2932A))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { showResetDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.cardBackground)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reiniciar",
                        modifier = Modifier.padding(end = 8.dp),
                        tint = colorResource(id = R.color.textColorPrimary)
                    )
                    Text(
                        "Reiniciar Treintena",
                        color = colorResource(id = R.color.textColorPrimary)  // Color del texto
                    )
                }

                // Diálogo de confirmación
                if (showResetDialog) {
                    AlertDialog(
                        onDismissRequest = { showResetDialog = false },
                        title = { Text("Reiniciar Treintena") },
                        text = {
                            Text(
                                "¿Estás seguro de que deseas reiniciar la Treintena? " +
                                        "Esto desmarcará todos los días completados."
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    viewModel.resetAllDays()
                                    showResetDialog = false
                                }
                            ) {
                                Text(
                                    "Sí, reiniciar",
                                    color = colorResource(id = R.color.textColorPrimary)
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showResetDialog = false }) {
                                Text(
                                    "Cancelar",
                                    color = colorResource(id = R.color.textColorPrimary)
                                )
                            }
                        }
                    )
                }

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
        },
        tabletContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFC2932A))
                    .padding(24.dp)
            ) {
                Button(
                    onClick = { showResetDialog = true },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reiniciar",
                        modifier = Modifier.padding(end = 8.dp),
                        tint = colorResource(id = R.color.textColorPrimary)
                    )
                    Text(
                        "Reiniciar Treintena",
                        color = colorResource(id = R.color.textColorPrimary)  // Color del texto
                    )
                }
                
                // Diálogo de confirmación
                if (showResetDialog) {
                    AlertDialog(
                        onDismissRequest = { showResetDialog = false },
                        title = { Text("Reiniciar Treintena") },
                        text = {
                            Text(
                                "¿Estás seguro de que deseas reiniciar la Treintena? " +
                                        "Esto desmarcará todos los días completados."
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    viewModel.resetAllDays()
                                    showResetDialog = false
                                }
                            ) {
                                Text(
                                    "Sí, reiniciar",
                                    color = colorResource(id = R.color.textColorPrimary)
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showResetDialog = false }) {
                                Text(
                                    "Cancelar",
                                    color = colorResource(id = R.color.textColorPrimary)
                                )
                            }
                        }
                    )
                }

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
    )
}

@Composable
private fun MeditationDaysList(
    meditations: List<Meditation>,
    selectedDay: Int?,
    onDaySelected: (Meditation) -> Unit
) {
    LazyColumn(
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
    val backColor = if (meditation.isCompleted) {
        colorResource(id = R.color.cardBackground).copy(alpha = 0.7f)
    } else {
        colorResource(id = R.color.cardBackground)
    }

    val iconState = when {
        meditation.isConsecration && meditation.isCompleted -> IconState(
            vector = Icons.Filled.CheckCircle,
            tint = Color(0xFF4CAF50),
            description = "Consagración completada"
        )

        meditation.isConsecration -> IconState(
            vector = Icons.Outlined.CheckCircle,  // Cambiamos de Lock a RadioButtonUnchecked para la consagración
            tint = Color.White,
            description = "Consagración disponible"
        )

        meditation.isCompleted -> IconState(
            vector = Icons.Filled.CheckCircle,
            tint = Color(0xFF4CAF50),
            description = "Día completado"
        )

        meditation.isLocked -> IconState(
            vector = Icons.Default.Lock,
            tint = Color.Gray,
            description = "Día bloqueado"
        )

        else -> IconState(
            vector = Icons.Outlined.CheckCircle,
            tint = Color.White,
            description = "Día disponible"
        )
    }

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
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = meditation.day,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                Text(
                    text = meditation.meditationDay,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            Icon(
                imageVector = iconState.vector,
                contentDescription = iconState.description,
                tint = iconState.tint,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
            )
        }
    }
}

private data class IconState(
    val vector: ImageVector,
    val tint: Color,
    val description: String
)