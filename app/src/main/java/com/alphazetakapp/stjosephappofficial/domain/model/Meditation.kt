package com.alphazetakapp.stjosephappofficial.domain.model

data class Meditation(
    val id: Int,
    val dayNum: Int,
    val day: String,
    val meditationDay: String,
    val imageResId: Int,
    val audioResId: Int,
    val dailyRecord: Int? = 0,
    val isCompleted: Boolean = false
)