package com.alphazetakapp.stjosephappofficial.presentation.settings

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alphazetakapp.stjosephappofficial.notification.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationUiState())
    val uiState: StateFlow<NotificationUiState> = _uiState.asStateFlow()

    init {
        checkNotificationStatus()
    }

    fun checkNotificationStatus() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true
            )
            
            try {
                val areEnabled = notificationRepository.areNotificationsEnabled()
                val token = notificationRepository.getFCMToken().getOrNull()
                
                _uiState.value = _uiState.value.copy(
                    areNotificationsEnabled = areEnabled,
                    fcmToken = token,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun subscribeToNotifications() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val result = notificationRepository.subscribeToNotifications()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    message = result.getOrNull(),
                    error = result.exceptionOrNull()?.message
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun unsubscribeFromNotifications() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val result = notificationRepository.unsubscribeFromNotifications()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    message = result.getOrNull(),
                    error = result.exceptionOrNull()?.message
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun clearMessage() {
        _uiState.value = _uiState.value.copy(message = null, error = null)
    }
}

data class NotificationUiState(
    val areNotificationsEnabled: Boolean = false,
    val fcmToken: String? = null,
    val isLoading: Boolean = false,
    val message: String? = null,
    val error: String? = null
)
