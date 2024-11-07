package com.alphazetakapp.stjosephappofficial.domain.usecase

import com.alphazetakapp.stjosephappofficial.domain.repository.MeditationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastCompletedDayUseCase @Inject constructor(
    private val repository: MeditationRepository
) {
    suspend operator fun invoke(): Flow<Int> {
        return repository.getLastCompletedDay()
    }
}