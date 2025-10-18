package com.alphazetakapp.stjosephappofficial.presentation.meditation.detail

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.data.datasource.remote.FirebaseStorageService
import com.alphazetakapp.stjosephappofficial.data.model.AudioFileMapping
import com.alphazetakapp.stjosephappofficial.datastore.StoreEndDay
import com.alphazetakapp.stjosephappofficial.domain.usecase.GetMeditationsUseCase
import com.alphazetakapp.stjosephappofficial.domain.usecase.SetDayCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeditationViewModel @Inject constructor(
    private val storeEndDay: StoreEndDay,
    @ApplicationContext private val context: Context,
    private val getMeditationsUseCase: GetMeditationsUseCase,
    private val setDayCompletedUseCase: SetDayCompletedUseCase,
    private val firebaseStorageService: FirebaseStorageService
): ViewModel() {

    private val _uiState = MutableStateFlow<MeditationDetailState>(MeditationDetailState.Loading)
    val uiState: StateFlow<MeditationDetailState> = _uiState.asStateFlow()

    private val mediaPlayers = mutableMapOf<AudioType, MediaPlayer?>()
    private val _playbackStates = MutableStateFlow<Map<AudioType, PlaybackState>>(emptyMap())
    val playbackStates: StateFlow<Map<AudioType, PlaybackState>> = _playbackStates.asStateFlow()
    
    // Estado para el progreso de descarga de audios
    private val _downloadStates = MutableStateFlow<Map<AudioType, DownloadState>>(emptyMap())
    val downloadStates: StateFlow<Map<AudioType, DownloadState>> = _downloadStates.asStateFlow()
    
    // Variable para almacenar el día actual
    private var currentDayNum: Int = 1

    fun loadMeditationData(dayNum: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = MeditationDetailState.Loading
                currentDayNum = dayNum
                
                // Combinamos la carga de meditación con el estado de completado
                combine(
                    flowOf(loadTexts(dayNum)),
                    storeEndDay.getSwitchStateForDay(dayNum)
                ) { meditationDetail, isCompleted ->
                    meditationDetail.copy(isCompleted = isCompleted)
                }.collect { meditation ->
                    _uiState.value = MeditationDetailState.Success(meditation)
                    
                    // Precargar audios para este día
                    preloadAudiosForDay(dayNum)
                }
            } catch (e: Exception) {
                _uiState.value = MeditationDetailState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun toggleDayCompletion(dayNum: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            setDayCompletedUseCase(dayNum, isCompleted)
                .collect{ _ ->
                    loadMeditationData(dayNum)
                }
        }
    }

    fun handleAudioAction(audioType: AudioType, action: AudioAction) {
        when (action) {
            is AudioAction.Play -> playAudio(audioType)
            is AudioAction.Pause -> pauseAudio(audioType)
            is AudioAction.Stop -> stopAudio(audioType)
            is AudioAction.ChangeSpeed -> changePlaybackSpeed(audioType, action.speed)
        }
    }

    /**
     * Precarga los audios para un día específico
     */
    private suspend fun preloadAudiosForDay(dayNum: Int) {
        val audioTypes = listOf(
            AudioType.ROSARY,
            AudioType.LITANIES,
            AudioType.FINAL_PRAY,
            AudioType.DAILY_MEDITATION
        )
        
        audioTypes.forEach { audioType ->
            updateDownloadState(audioType) { DownloadState.Downloading }
            try {
                val fileName = AudioFileMapping.getFileName(audioType, dayNum)
                val audioPath = firebaseStorageService.getAudioUrl(fileName)
                updateDownloadState(audioType) { DownloadState.Downloaded(audioPath) }
            } catch (e: Exception) {
                updateDownloadState(audioType) { DownloadState.Error(e.message ?: "Error desconocido") }
            }
        }
    }
    
    /**
     * Obtiene la ruta del audio (local o descargado)
     */
    private suspend fun getAudioPath(audioType: AudioType): String? {
        val downloadState = _downloadStates.value[audioType]
        return when (downloadState) {
            is DownloadState.Downloaded -> downloadState.filePath
            is DownloadState.Downloading -> {
                // Esperar a que termine la descarga
                val fileName = AudioFileMapping.getFileName(audioType, currentDayNum)
                try {
                    firebaseStorageService.getAudioUrl(fileName)
                } catch (e: Exception) {
                    null
                }
            }
            is DownloadState.Error -> null
            null -> {
                // Iniciar descarga si no está en ningún estado
                val fileName = AudioFileMapping.getFileName(audioType, currentDayNum)
                try {
                    firebaseStorageService.getAudioUrl(fileName)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }

    private suspend fun initializeMediaPlayer(audioType: AudioType) {
        if (mediaPlayers[audioType] == null) {
            val audioPath = getAudioPath(audioType)
            if (audioPath != null) {
                try {
                    val mediaPlayer = MediaPlayer()
                    mediaPlayer.setDataSource(audioPath)
                    mediaPlayer.prepare()
                    mediaPlayers[audioType] = mediaPlayer
                } catch (e: Exception) {
                    _uiState.value = MeditationDetailState.Error("Error inicializando reproductor: ${e.message}")
                }
            } else {
                _uiState.value = MeditationDetailState.Error("No se pudo obtener el audio")
            }
        }
    }


    private fun playAudio(audioType: AudioType) {
        viewModelScope.launch {
            try {
                initializeMediaPlayer(audioType)
                mediaPlayers[audioType]?.let { player ->
                    player.start()
                    updatePlaybackState(audioType) { current ->
                        current.copy(isPlaying = true)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MeditationDetailState.Error("Error reproduciendo audio: ${e.message}")
            }
        }
    }

    private fun pauseAudio(audioType: AudioType) {
        viewModelScope.launch {
            try {
                mediaPlayers[audioType]?.let { player ->
                    player.pause()
                    updatePlaybackState(audioType) { current ->
                        current.copy(isPlaying = false)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MeditationDetailState.Error("Error pausando audio: ${e.message}")
            }
        }
    }

    private fun stopAudio(audioType: AudioType) {
        viewModelScope.launch {
            try {
                mediaPlayers[audioType]?.let { player ->
                    player.stop()
                    player.prepare()
                    updatePlaybackState(audioType) { current ->
                        current.copy(isPlaying = false)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MeditationDetailState.Error("Error deteniendo audio: ${e.message}")
            }
        }
    }

    private fun changePlaybackSpeed(audioType: AudioType, speed: Float) {
        viewModelScope.launch {
            try {
                mediaPlayers[audioType]?.let { player ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        player.playbackParams = player.playbackParams.setSpeed(speed)
                        updatePlaybackState(audioType) { current ->
                            current.copy(speed = speed)
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MeditationDetailState.Error("Error cambiando velocidad: ${e.message}")
            }
        }
    }

    private fun loadTexts(dayNum: Int): MeditationDetail {
        return try {
            MeditationDetail(
                dayNum = dayNum,
                meditationText = context.getString(
                    context.resources.getIdentifier(
                        "meditation_day_$dayNum",
                        "string",
                        context.packageName
                    )
                ),
                rosaryText = context.getString(
                    context.resources.getIdentifier(
                        "meditation_screen_text_rosary",
                        "string",
                        context.packageName
                    )
                ),
                litaniesText = context.getString(
                    context.resources.getIdentifier(
                        "meditation_screen_text_letanies",
                        "string",
                        context.packageName
                    )
                ),
                finalPrayerText = context.getString(
                    context.resources.getIdentifier(
                        "meditation_screen_text_final_pray",
                        "string",
                        context.packageName
                    )
                ),
                isCompleted = false,
                audioResId = context.resources.getIdentifier(
                    "listenmed$dayNum",
                    "raw",
                    context.packageName
                )
            )
        } catch (e: Exception) {
            throw Exception("Error cargando textos: ${e.message}")
        }
    }

    private fun updatePlaybackState(audioType: AudioType, update: (PlaybackState) -> PlaybackState) {
        _playbackStates.value = _playbackStates.value.toMutableMap().apply {
            put(audioType, update(get(audioType) ?: PlaybackState()))
        }
    }
    
    private fun updateDownloadState(audioType: AudioType, update: (DownloadState?) -> DownloadState) {
        _downloadStates.value = _downloadStates.value.toMutableMap().apply {
            put(audioType, update(get(audioType)))
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayers.values.forEach { it?.release() }
        mediaPlayers.clear()
    }
}

sealed class MeditationDetailState {
    data object Loading : MeditationDetailState()
    data class Success(val meditation: MeditationDetail) : MeditationDetailState()
    data class Error(val message: String) : MeditationDetailState()
}

data class MeditationDetail(
    val dayNum: Int,
    val meditationText: String,
    val rosaryText: String,
    val litaniesText: String,
    val finalPrayerText: String,
    val isCompleted: Boolean,
    val audioResId: Int
)

enum class AudioType {
    ROSARY, LITANIES, DAILY_MEDITATION, FINAL_PRAY
}

sealed class AudioAction {
    data object Play : AudioAction()
    data object Pause : AudioAction()
    data object Stop : AudioAction()
    data class ChangeSpeed(val speed: Float) : AudioAction()
}

data class PlaybackState(
    val isPlaying: Boolean = false,
    val speed: Float = 1.0f
)

sealed class DownloadState {
    data object Downloading : DownloadState()
    data class Downloaded(val filePath: String) : DownloadState()
    data class Error(val message: String) : DownloadState()
}
