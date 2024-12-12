package com.alphazetakapp.stjosephappofficial.domain.usecase

import com.alphazetakapp.stjosephappofficial.domain.repository.MeditationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetDayCompletedUseCase @Inject constructor(
    private val repository: MeditationRepository
) {
    suspend operator fun invoke(dayNum: Int, isCompleted: Boolean): Flow<Int> {
        return repository.setDayCompleted(dayNum, isCompleted)
    }
}