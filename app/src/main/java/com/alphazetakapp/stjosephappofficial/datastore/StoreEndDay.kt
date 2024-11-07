package com.alphazetakapp.stjosephappofficial.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val DATASTORE_NAME = "SwitchState"

class StoreEndDay(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "end_day_prefs")

    companion object {
        private val END_DAY_KEY = intPreferencesKey("end_day")
    }

    // Crea una función para obtener la clave única para cada día
    private fun switchStateKeyForDay(day: Int) =
        booleanPreferencesKey("switch_state_$day")

    // Crea una función para obtener el estado del Switch para un día específico
    fun getSwitchStateForDay(day: Int): Flow<Boolean> {
        val switchStateKey = switchStateKeyForDay(day)
        return context.dataStore.data.map { preferences ->
            preferences[switchStateKey] ?: false
        }
    }

    // Crea una función para guardar el estado del Switch para un día específico
    suspend fun saveSwitchStateForDay(day: Int, isOn: Boolean) {
        val switchStateKey = switchStateKeyForDay(day)
        context.dataStore.edit { preferences ->
            preferences[switchStateKey] = isOn
        }
    }

    suspend fun saveEndDay(day: Int) {
        context.dataStore.edit { preferences ->
            preferences[END_DAY_KEY] = day
        }
    }

    fun getEndDay(): Flow<Int> {
        return context.dataStore.data
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
}