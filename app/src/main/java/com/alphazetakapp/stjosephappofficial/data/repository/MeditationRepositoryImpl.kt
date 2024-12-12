package com.alphazetakapp.stjosephappofficial.data.repository

import com.alphazetakapp.stjosephappofficial.data.datasource.local.MeditationLocalDataSource
import com.alphazetakapp.stjosephappofficial.datastore.StoreEndDay
import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import com.alphazetakapp.stjosephappofficial.domain.repository.MeditationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MeditationRepositoryImpl @Inject constructor(
    private val localDataSource: MeditationLocalDataSource,
    private val storeEndDay: StoreEndDay,
): MeditationRepository {

    override suspend fun getMeditations(): Flow<List<Meditation>> = flow {
        val meditations = localDataSource.getMeditations().map { it.toDomain() }
        emit(meditations)
    }

    override suspend fun getLastCompletedDay(): Flow<Int> {
        return storeEndDay.getEndDay()
    }

    override suspend fun setDayCompleted(dayNum: Int, isCompleted: Boolean): Flow<Int> = flow {
        val currentLastDay = storeEndDay.getEndDay().first()

        // Solo permitimos marcar como completado si:
        // - Es el día siguiente al último completado
        // - O es el primer día
        // - O estamos desmarcando el último día completado

        if (isCompleted) {
            // Si se marca como completado, guardamos el día actual
            storeEndDay.saveEndDay(dayNum)
            emit(dayNum)
        } else {
            val currentLastDay = storeEndDay.getEndDay().first()
            if (currentLastDay == dayNum){
                storeEndDay.saveEndDay(dayNum - 1)
                emit(dayNum - 1)
            } else {
                emit(currentLastDay)
            }
        }
    }

}