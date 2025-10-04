package com.alphazetakapp.stjosephappofficial.notification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor(
    private val context: Context
) {
    
    /**
     * Verifica si las notificaciones están habilitadas
     */
    fun areNotificationsEnabled(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true // Para versiones anteriores a Android 13, asumimos que están habilitadas
        }
    }

    /**
     * Suscribe al usuario al topic general de notificaciones
     */
    suspend fun subscribeToNotifications(): Result<String> {
        return try {
            FirebaseMessaging.getInstance().subscribeToTopic("devocionario_general").await()
            Result.success("Suscrito exitosamente a las notificaciones")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Desuscribe al usuario del topic general
     */
    suspend fun unsubscribeFromNotifications(): Result<String> {
        return try {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("devocionario_general").await()
            Result.success("Desuscrito exitosamente de las notificaciones")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtiene el token FCM actual
     */
    suspend fun getFCMToken(): Result<String> {
        return try {
            val token = FirebaseMessaging.getInstance().token.await()
            Result.success(token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Suscribe al usuario a notificaciones de días específicos
     */
    suspend fun subscribeToDayNotifications(dayNum: Int): Result<String> {
        return try {
            val topic = "day_$dayNum"
            FirebaseMessaging.getInstance().subscribeToTopic(topic).await()
            Result.success("Suscrito a notificaciones del día $dayNum")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
