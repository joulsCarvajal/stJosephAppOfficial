package com.alphazetakapp.stjosephappofficial.presentation.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("main_screen") // Mantenemos tu ruta actual
    object Preparation : Screen("prep_meditation")
    object Days : Screen("days_screen")
    object MeditationNav : Screen("meditation_screen?day={day}&dayNum={dayNum}&dailyRecord={dailyRecord}") {
        fun createRoute(dayNum: Int, day: String, dailyRecord: Int) =
            "meditation_screen?day=$day&dayNum=$dayNum&dailyRecord=$dailyRecord"
    }
    object TheNew : Screen("the_new_screen")
}