package com.alphazetakapp.stjosephappofficial.presentation.meditation.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.ReadMeditationScreen.ExpandableTextLitanies
import com.alphazetakapp.stjosephappofficial.ReadMeditationScreen.ExpandableTextRosary
import com.alphazetakapp.stjosephappofficial.presentation.common.ErrorMessage
import com.alphazetakapp.stjosephappofficial.presentation.common.LoadingIndicator
import com.alphazetakapp.stjosephappofficial.ui.BannerApp
import kotlin.math.max

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MeditationScreen(
    dayNum: Int,
//    day: String,
//    dailyRecord: Int,
    viewModel: MeditationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val playbackStates by viewModel.playbackStates.collectAsState()

    LaunchedEffect(dayNum) {
        viewModel.loadMeditationData(dayNum)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.backgroundColorApp))
            .padding(12.dp)
    ) {
        when (uiState) {
            is MeditationDetailState.Loading -> {
                LoadingIndicator()
            }

            is MeditationDetailState.Error -> {
                ErrorMessage(message = (uiState as MeditationDetailState.Error).message)
            }

            is MeditationDetailState.Success -> {
                val meditationState = uiState as MeditationDetailState.Success
                MeditationContent(
                    meditation = meditationState.meditation,
                    playbackStates = playbackStates,
                    onAudioAction = { audioType, action ->
                        viewModel.handleAudioAction(audioType, action)
                    },
                    onCompletionToggle = { isCompleted ->
                        viewModel.toggleDayCompletion(dayNum, isCompleted)
                    }
                )
            }
        }
    }
}

@Composable
private fun MeditationContent(
    meditation: MeditationDetail,
    playbackStates: Map<AudioType, PlaybackState>,
    onAudioAction: (AudioType, AudioAction) -> Unit,
    onCompletionToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Banner
        BannerApp()
        Spacer(modifier = Modifier.height(8.dp))

        // Título
        MeditationTitle(dayNum = meditation.dayNum)
        Spacer(modifier = Modifier.height(16.dp))

        // Rosario
        ExpandableSection(
            title = "Rosario a San José",
            content = {
                ExpandableSectionContent(text = meditation.rosaryText)
            },
            audioControl = {
                AudioControls(
                    playbackState = playbackStates[AudioType.ROSARY] ?: PlaybackState(),
                    onAction = { action -> onAudioAction(AudioType.ROSARY, action) }
                )
            }
        )

        // Letanías
        ExpandableSection(
            title = "Letanías",
            content = {
                ExpandableSectionContent(text = meditation.litaniesText)
            },
            audioControl = {
                AudioControls(
                    playbackState = playbackStates[AudioType.LITANIES] ?: PlaybackState(),
                    onAction = { action -> onAudioAction(AudioType.LITANIES, action) }
                )
            }
        )

        // Meditación del día
        ExpandableSection(
            title = "Meditación Día ${meditation.dayNum}",
            content = {
                ExpandableSectionContent(text = meditation.meditationText)
            },
            audioControl = {
                AudioControls(
                    playbackState = playbackStates[AudioType.DAILY_MEDITATION] ?: PlaybackState(),
                    onAction = { action -> onAudioAction(AudioType.DAILY_MEDITATION, action) }
                )
            }
        )

        // Oración Final
        ExpandableSection(
            title = "Oración Final",
            content = {
                ExpandableSectionContent(text = meditation.finalPrayerText)
            },
            audioControl = {
                AudioControls(
                    playbackState = playbackStates[AudioType.FINAL_PRAY] ?: PlaybackState(),
                    onAction = { action -> onAudioAction(AudioType.FINAL_PRAY, action) }
                )
            }
        )

        // Completado
        CompletionSwitch(
            isCompleted = meditation.isCompleted,
            dayNum = meditation.dayNum,
            onToggle = onCompletionToggle
        )
    }
}

@Composable
fun CompletionSwitch(
    isCompleted: Boolean,
    dayNum: Int,
    onToggle: (Boolean) -> Unit
) {
    val backgroundColor = if (isCompleted) Color(0xFFF2A71B) else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Switch(
                checked = isCompleted,
                onCheckedChange = { isChecked ->
                    onToggle(isChecked)
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = if (isCompleted)
                    "DIA $dayNum, TERMINADO!!!"
                else
                    "MARCA EL DIA $dayNum COMO TERMINADO",
                color = Color.White,
                fontStyle = FontStyle.Italic,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MeditationTitle(dayNum: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.contrastColorApp))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            border = BorderStroke(0.4.dp, color = Color.Transparent),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.contrastColorApp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "TREINTENA A SAN JOSÉ \n Día $dayNum",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun AudioControls(
    playbackState: PlaybackState,
    onAction: (AudioAction) -> Unit
) {
    val iconColor = colorResource(id = R.color.backgroundColorApp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Play/Pause
        IconButton(
            onClick = {
                if (playbackState.isPlaying) {
                    onAction(AudioAction.Pause)
                } else {
                    onAction(AudioAction.Play)
                }
            }
        ) {
            Icon(
                painter = painterResource(
                    id = if (playbackState.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                ),
                contentDescription = if (playbackState.isPlaying) "Pausar" else "Reproducir",
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        // Stop
        IconButton(onClick = { onAction(AudioAction.Stop) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_stop),
                contentDescription = "Detener",
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        // Velocidad +
        IconButton(
            onClick = {
                onAction(AudioAction.ChangeSpeed(playbackState.speed + 0.25f))
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rapido),
                contentDescription = "Aumentar velocidad",
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        // Velocidad -
        IconButton(
            onClick = {
                onAction(AudioAction.ChangeSpeed(max(0.25f, playbackState.speed - 0.25f)))
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.lento_menos),
                contentDescription = "Reducir velocidad",
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
private fun ExpandableSection(
    title: String,
    content: @Composable () -> Unit,
    audioControl: @Composable () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
        )
    ) {
        // Header siempre visible
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Contraer" else "Expandir"
            )
        }

        // Contenido expandible
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Área de contenido
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp, max = 300.dp)
                ) {
                    content()
                }

                // Separador
                Spacer(modifier = Modifier.height(8.dp))

                // Controles de audio
                audioControl()

                // Espaciado final
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// Composable específico para el contenido
@Composable
private fun ExpandableSectionContent(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}