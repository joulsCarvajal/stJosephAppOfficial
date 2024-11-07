package com.alphazetakapp.stjosephappofficial.domain.repository

import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import kotlinx.coroutines.flow.Flow

interface MeditationRepository {
    suspend fun getMeditations(): Flow<List<Meditation>>
    suspend fun getLastCompletedDay(): Flow<Int>
    suspend fun setDayCompleted(dayNum: Int)
}