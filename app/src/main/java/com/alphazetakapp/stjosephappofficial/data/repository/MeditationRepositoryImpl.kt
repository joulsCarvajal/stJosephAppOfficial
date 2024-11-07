package com.alphazetakapp.stjosephappofficial.data.repository

import com.alphazetakapp.stjosephappofficial.data.datasource.local.MeditationLocalDataSource
import com.alphazetakapp.stjosephappofficial.datastore.StoreEndDay
import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import com.alphazetakapp.stjosephappofficial.domain.repository.MeditationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MeditationRepositoryImpl @Inject constructor(
    private val localDataSource: MeditationLocalDataSource,
    private val storeEndDay: StoreEndDay
): MeditationRepository {

    override suspend fun getMeditations(): Flow<List<Meditation>> = flow {
        val meditations = localDataSource.getMeditations().map { it.toDomain() }
        emit(meditations)
    }

    override suspend fun getLastCompletedDay(): Flow<Int> {
        return storeEndDay.getEndDay()
    }

    override suspend fun setDayCompleted(dayNum: Int) {
        storeEndDay.saveEndDay(dayNum)
    }

}