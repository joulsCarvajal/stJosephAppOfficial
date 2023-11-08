package com.alphazetakapp.stjosephappofficial.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "SwitchState"

class StoreEndDay(private val context: Context) {
    companion object {
        private val Context.dataStore by preferencesDataStore(DATASTORE_NAME)
    }

    fun getMeditationDayState(dayNum: Int): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[getMeditationDayKey(dayNum)] ?: false
            }
    }

    suspend fun saveMeditationDayState(dayNum: Int, isSelected: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[getMeditationDayKey(dayNum)] = isSelected
        }
    }

    private fun getMeditationDayKey(dayNum: Int): Preferences.Key<Boolean> {
        return booleanPreferencesKey("meditation_day_state_$dayNum")
    }
}