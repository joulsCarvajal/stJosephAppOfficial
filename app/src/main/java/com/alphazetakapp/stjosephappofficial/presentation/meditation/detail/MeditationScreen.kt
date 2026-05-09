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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.draw.clip
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
import com.alphazetakapp.stjosephappofficial.presentation.common.ResponsiveLayout
import com.alphazetakapp.stjosephappofficial.ui.BannerApp
import kotlin.math.max

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MeditationScreen(
    dayNum: Int,
    viewModel: MeditationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val playbackStates by viewModel.playbackStates.collectAsState()
    val downloadStates by viewModel.downloadStates.collectAsState()

    LaunchedEffect(dayNum) {
        viewModel.loadMeditationData(dayNum)
    }

    ResponsiveLayout(
        content = {
            // Diseño para móviles
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.backgroundColorApp))
            ) {
                when (uiState) {
                    is MeditationDetailState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingIndicator()
                        }
                    }
                    is MeditationDetailState.Error -> {
                        ErrorMessage(message = (uiState as MeditationDetailState.Error).message)
                    }
                    is MeditationDetailState.Success -> {
                        val meditationState = uiState as MeditationDetailState.Success
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            BannerApp(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                            
                            MeditationTitle(dayNum = dayNum)
                            
                            MeditationContent(
                                meditation = meditationState.meditation,
                                playbackStates = playbackStates,
                                downloadStates = downloadStates,
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
        },
        tabletContent = {
            // Diseño para tablets
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.backgroundColorApp))
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
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            // Panel izquierdo
                            Column(
                                modifier = Modifier
                                    .weight(0.4f)
                                    .padding(end = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                MeditationTitle(dayNum)
                                Spacer(modifier = Modifier.height(16.dp))
                                BannerApp()
                                AudioControls(
                                    playbackState = playbackStates[AudioType.ROSARY] ?: PlaybackState(),
                                    downloadState = downloadStates[AudioType.ROSARY],
                                    onAction = { action -> 
                                        viewModel.handleAudioAction(AudioType.ROSARY, action) 
                                    }
                                )
                            }
                            
                            // Panel derecho
                            Column(
                                modifier = Modifier
                                    .weight(0.6f)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                MeditationContent(
                                    meditation = meditationState.meditation,
                                    playbackStates = playbackStates,
                                    downloadStates = downloadStates,
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
            }
        }
    )
}

@Composable
private fun MeditationContent(
    meditation: MeditationDetail,
    playbackStates: Map<AudioType, PlaybackState>,
    downloadStates: Map<AudioType, DownloadState>,
    onAudioAction: (AudioType, AudioAction) -> Unit,
    onCompletionToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Secciones expandibles
        ExpandableSection(
            title = "Rosario a San José",
            content = {
                ExpandableSectionContent(text = meditation.rosaryText)
            },
            audioControl = {
                AudioControls(
                    playbackState = playbackStates[AudioType.ROSARY] ?: PlaybackState(),
                    downloadState = downloadStates[AudioType.ROSARY],
                    onAction = { action -> onAudioAction(AudioType.ROSARY, action) }
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExpandableSection(
            title = "Letanías",
            content = {
                ExpandableSectionContent(text = meditation.litaniesText)
            },
            audioControl = {
                AudioControls(
                    playbackState = playbackStates[AudioType.LITANIES] ?: PlaybackState(),
                    downloadState = downloadStates[AudioType.LITANIES],
                    onAction = { action -> onAudioAction(AudioType.LITANIES, action) }
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExpandableSection(
            title = "Meditación Día ${meditation.dayNum}",
            content = {
                ExpandableSectionContent(text = meditation.meditationText)
            },
            audioControl = {
                AudioControls(
                    playbackState = playbackStates[AudioType.DAILY_MEDITATION] ?: PlaybackState(),
                    downloadState = downloadStates[AudioType.DAILY_MEDITATION],
                    onAction = { action -> onAudioAction(AudioType.DAILY_MEDITATION, action) }
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExpandableSection(
            title = "Oración Final",
            content = {
                ExpandableSectionContent(text = meditation.finalPrayerText)
            },
            audioControl = {
                AudioControls(
                    playbackState = playbackStates[AudioType.FINAL_PRAY] ?: PlaybackState(),
                    downloadState = downloadStates[AudioType.FINAL_PRAY],
                    onAction = { action -> onAudioAction(AudioType.FINAL_PRAY, action) }
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

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
    val completedGreen = Color(0xFF4CAF50)
    val cardBg = if (isCompleted)
        completedGreen.copy(alpha = 0.12f)
    else
        MaterialTheme.colorScheme.surface

    val borderColor = if (isCompleted) completedGreen else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle(!isCompleted) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        border = BorderStroke(1.5.dp, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = if (isCompleted) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = if (isCompleted) completedGreen else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                modifier = Modifier.size(32.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (isCompleted) "Día $dayNum completado" else "Marcar día $dayNum como completado",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = if (isCompleted) completedGreen else MaterialTheme.colorScheme.onSurface
                )
                if (!isCompleted) {
                    Text(
                        text = "Toca para registrar tu progreso",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            }

            Switch(
                checked = isCompleted,
                onCheckedChange = { onToggle(it) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = completedGreen,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            )
        }
    }
}

@Composable
fun MeditationTitle(dayNum: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.backgroundColorApp))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            border = BorderStroke(0.4.dp, color = Color.Transparent),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.cardBackground)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.colorPrimary)),
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
    downloadState: DownloadState?,
    onAction: (AudioAction) -> Unit
) {
    val iconColor = colorResource(id = R.color.textColorPrimary)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Indicador de estado de descarga
        when (downloadState) {
            is DownloadState.Downloading -> {
                Text(
                    text = "Descargando...",
                    color = Color.Yellow,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            is DownloadState.Error -> {
                Text(
                    text = "Error: ${downloadState.message}",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            is DownloadState.Downloaded -> {
                Text(
                    text = "✓ Listo",
                    color = Color.Green,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            null -> {
                Text(
                    text = "Preparando...",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
        
        // Play/Pause
        IconButton(
            onClick = {
                if (playbackState.isPlaying) {
                    onAction(AudioAction.Pause)
                } else {
                    onAction(AudioAction.Play)
                }
            },
            enabled = downloadState is DownloadState.Downloaded
        ) {
            Icon(
                painter = painterResource(
                    id = if (playbackState.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                ),
                contentDescription = if (playbackState.isPlaying) "Pausar" else "Reproducir",
                tint = if (downloadState is DownloadState.Downloaded) iconColor else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        // Stop
        IconButton(
            onClick = { onAction(AudioAction.Stop) },
            enabled = downloadState is DownloadState.Downloaded
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_stop),
                contentDescription = "Detener",
                tint = if (downloadState is DownloadState.Downloaded) iconColor else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        // Velocidad +
        IconButton(
            onClick = {
                onAction(AudioAction.ChangeSpeed(playbackState.speed + 0.25f))
            },
            enabled = downloadState is DownloadState.Downloaded
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rapido),
                contentDescription = "Aumentar velocidad",
                tint = if (downloadState is DownloadState.Downloaded) iconColor else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        // Velocidad -
        IconButton(
            onClick = {
                onAction(AudioAction.ChangeSpeed(max(0.25f, playbackState.speed - 0.25f)))
            },
            enabled = downloadState is DownloadState.Downloaded
        ) {
            Icon(
                painter = painterResource(id = R.drawable.lento_menos),
                contentDescription = "Reducir velocidad",
                tint = if (downloadState is DownloadState.Downloaded) iconColor else Color.Gray,
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
            containerColor = colorResource(id = R.color.backgroundColorApp)
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
                color = colorResource(id = R.color.textColorPrimary)
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Contraer" else "Expandir",
                tint = colorResource(id = R.color.textColorPrimary)
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
            modifier = Modifier.padding(bottom = 8.dp),
            color = colorResource(id = R.color.textColorSecondary)
        )
    }
}