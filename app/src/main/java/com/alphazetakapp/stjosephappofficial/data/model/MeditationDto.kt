package com.alphazetakapp.stjosephappofficial.data.model

import com.alphazetakapp.stjosephappofficial.domain.model.Meditation

data class MeditationDto (
    val dayNum: Int,
    val day: String,
    val meditationDay: String,
    val imageResId: Int,
    val audioResId: Int,
    val isConsecration: Boolean = false
){
    fun toDomain() = Meditation(
        id = dayNum,
        dayNum = dayNum,
        day = day,
        meditationDay = meditationDay,
        imageResId = imageResId,
        audioResId = audioResId
    )
}