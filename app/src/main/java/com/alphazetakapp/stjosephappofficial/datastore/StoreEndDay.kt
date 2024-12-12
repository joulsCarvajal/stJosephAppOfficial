package com.alphazetakapp.stjosephappofficial.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val DATASTORE_NAME = "SwitchState"

class StoreEndDay @Inject constructor(private val context: Context) {

    // Definimos el DataStore a nivel de propiedad para mejor encapsulación
    private val Context.dataStore by preferencesDataStore(name = "end_day_prefs")

    companion object {
        private val END_DAY_KEY = intPreferencesKey("end_day")
        // Mantenemos la capacidad de tener estados individuales por día
        private fun switchStateKeyForDay(day: Int) = booleanPreferencesKey("switch_state_$day")
    }

    suspend fun resetAllDays() {
        context.dataStore.edit { preferences ->
            // Primero recolectamos todas las keys que necesitamos eliminar
            val keysToRemove = preferences.asMap().keys.filter { key ->
                key.name.startsWith("switch_state_")
            }.toSet() // Convertimos a Set para tener una copia inmutable

            // Ahora podemos eliminar las keys de forma segura
            keysToRemove.forEach { key ->
                preferences.remove(key)
            }

            // Finalmente, reseteamos el último día completado
            preferences[END_DAY_KEY] = 0
        }
    }



    /**
     * Obtiene el estado del switch para un día específico.
     * Ahora también considera el último día completado para mantener la coherencia.
     */
    fun getSwitchStateForDay(day: Int, isConsecration: Boolean = false): Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            val lastCompletedDay = preferences[END_DAY_KEY] ?: 0
            when {
                isConsecration -> lastCompletedDay >= 30  // Solo disponible si se completaron los 30 días
                else -> day <= lastCompletedDay
            }
        }

    /**
     * Guarda el estado del switch para un día específico.
     * También actualiza el último día completado si es necesario.
     */
    suspend fun saveSwitchStateForDay(day: Int, isOn: Boolean, isConsecration: Boolean = false) {
        context.dataStore.edit { preferences ->
            if (isConsecration) {
                // Solo permitir marcar la consagración si se completaron los 30 días
                val lastCompletedDay = preferences[END_DAY_KEY] ?: 0
                if (lastCompletedDay >= 30) {
                    preferences[switchStateKeyForDay(31)] = isOn  // Guardamos estado de consagración
                }
            } else {
                // Lógica normal para los días regulares
                // ... código existente ...
            }
        }
    }

    /**
     * Guarda el último día completado directamente.
     * Este método se usa principalmente para actualizaciones directas del progreso.
     */
    suspend fun saveEndDay(day: Int) {
        context.dataStore.edit { preferences ->
            preferences[END_DAY_KEY] = day

            // Actualizamos también los estados individuales para mantener la coherencia
            for (i in 1..30) {  // Asumiendo 30 días en total
                preferences[switchStateKeyForDay(i)] = i <= day
            }
        }
    }

    /**
     * Obtiene el último día completado.
     * Incluye manejo de errores y valor por defecto.
     */
    fun getEndDay(): Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[END_DAY_KEY] ?: 0
        }
}