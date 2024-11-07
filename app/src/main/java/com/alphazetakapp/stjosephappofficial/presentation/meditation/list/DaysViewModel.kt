package com.alphazetakapp.stjosephappofficial.presentation.meditation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import com.alphazetakapp.stjosephappofficial.domain.usecase.GetLastCompletedDayUseCase
import com.alphazetakapp.stjosephappofficial.domain.usecase.GetMeditationsUseCase
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
    private val getLastCompletedDayUseCase: GetLastCompletedDayUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DaysUiState>(DaysUiState.Loading)
    val uiState: StateFlow<DaysUiState> = _uiState.asStateFlow()

    private val _selectedDay = MutableStateFlow<Int?>(null)
    val selectedDay: StateFlow<Int?> = _selectedDay.asStateFlow()

    init {
        loadMeditations()
    }

    private fun loadMeditations() {
        viewModelScope.launch {
            try {
                combine(
                    getMeditationsUseCase(),
                    getLastCompletedDayUseCase()
                ) { meditations, lastCompletedDay ->
                    meditations.map { meditation ->
                        meditation.copy(isCompleted = meditation.dayNum <= lastCompletedDay)
                    }
                }.collect { meditations ->
                    _uiState.value = DaysUiState.Success(meditations)
                }
            } catch (e: Exception) {
                _uiState.value = DaysUiState.Error(e.message ?: "Error desconocido")
            }
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