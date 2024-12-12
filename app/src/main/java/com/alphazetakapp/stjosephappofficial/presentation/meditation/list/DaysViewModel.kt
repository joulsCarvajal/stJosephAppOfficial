package com.alphazetakapp.stjosephappofficial.presentation.meditation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphazetakapp.stjosephappofficial.datastore.StoreEndDay
import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import com.alphazetakapp.stjosephappofficial.domain.usecase.GetLastCompletedDayUseCase
import com.alphazetakapp.stjosephappofficial.domain.usecase.GetMeditationsUseCase
import com.alphazetakapp.stjosephappofficial.domain.usecase.SetDayCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaysViewModel @Inject constructor(
    private val getMeditationsUseCase: GetMeditationsUseCase,
    private val getLastCompletedDayUseCase: GetLastCompletedDayUseCase,
    private val setDayCompletedUseCase: SetDayCompletedUseCase,
    private val storeEndDay: StoreEndDay
) : ViewModel() {

    private val _uiState = MutableStateFlow<DaysUiState>(DaysUiState.Loading)
    val uiState: StateFlow<DaysUiState> = _uiState.asStateFlow()

    private val _selectedDay = MutableStateFlow<Int?>(null)
    val selectedDay: StateFlow<Int?> = _selectedDay.asStateFlow()

    init {
        observeCompletedDays()
    }

    private fun observeCompletedDays() {
        viewModelScope.launch {
            // Observamos el último día completado y actualizamos la UI cuando cambia
            getLastCompletedDayUseCase().collect { lastCompletedDay ->
                loadMeditations(lastCompletedDay)
            }
        }
    }

    private fun loadMeditations(lastCompletedDay: Int) {
        viewModelScope.launch {
            try {
                getMeditationsUseCase().collect { meditations ->
                    val updatedMeditations = meditations.map { meditation ->
                        meditation.copy(
                            isCompleted = meditation.dayNum <= lastCompletedDay,
                            isLocked = meditation.dayNum > lastCompletedDay + 1
                        )
                    }
                    _uiState.value = DaysUiState.Success(updatedMeditations)
                }
            } catch (e: Exception) {
                _uiState.value = DaysUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun resetAllDays() {
        viewModelScope.launch {
            // Reiniciamos el último día completado a 0
            storeEndDay.resetAllDays()
        }
    }

    fun onDaySelected(dayNum: Int) {
        _selectedDay.value = dayNum
    }
}

sealed class DaysUiState {
    data object Loading : DaysUiState()
    data class Success(val meditations: List<Meditation>) : DaysUiState()
    data class Error(val message: String) : DaysUiState()
}