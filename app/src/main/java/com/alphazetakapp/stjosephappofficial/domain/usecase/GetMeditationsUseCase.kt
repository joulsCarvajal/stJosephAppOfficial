package com.alphazetakapp.stjosephappofficial.domain.usecase

import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import com.alphazetakapp.stjosephappofficial.domain.repository.MeditationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMeditationsUseCase @Inject constructor(
    private val repository: MeditationRepository
) {
    suspend operator fun invoke(): Flow<List<Meditation>> {
        return repository.getMeditations()
    }
}