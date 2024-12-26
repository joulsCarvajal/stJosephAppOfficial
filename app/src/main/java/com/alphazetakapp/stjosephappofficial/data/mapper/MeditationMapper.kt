package com.alphazetakapp.stjosephappofficial.data.mapper

import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import com.alphazetakapp.stjosephappofficial.data.model.MeditationDto
import com.alphazetakapp.stjosephappofficial.presentation.meditation.detail.MeditationDetail
import javax.inject.Inject

class MeditationMapper @Inject constructor() {
    fun toDomain(dto: MeditationDto) = Meditation(
        id = dto.dayNum,
        dayNum = dto.dayNum,
        day = dto.day,
        meditationDay = dto.meditationDay,
        imageResId = dto.imageResId,
        audioResId = dto.audioResId,
        isConsecration = dto.isConsecration
    )

    fun MeditationDto.toDetail(): MeditationDetail {
        return MeditationDetail(
            dayNum = dayNum,
            meditationText = meditationDay,
            rosaryText = "...",
            litaniesText = "...",
            finalPrayerText = "...",
            isCompleted = false,
            audioResId = audioResId  // Incluye el ID del audio
        )
    }
}