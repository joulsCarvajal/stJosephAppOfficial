package com.alphazetakapp.stjosephappofficial.data.mapper

import com.alphazetakapp.stjosephappofficial.domain.model.Meditation
import com.alphazetakapp.stjosephappofficial.data.model.MeditationDto
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
}