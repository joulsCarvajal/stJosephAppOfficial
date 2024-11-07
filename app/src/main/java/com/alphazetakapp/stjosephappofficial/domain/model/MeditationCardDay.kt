package com.alphazetakapp.stjosephappofficial.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class MeditationCardDay(
    var dayNum: Int,
    var day: String,
    var meditationDay: String,
    @DrawableRes var photo: Int,
    @RawRes var dailyRecord: Int
)