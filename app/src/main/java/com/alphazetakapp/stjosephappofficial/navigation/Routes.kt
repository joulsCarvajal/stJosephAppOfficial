package com.alphazetakapp.stjosephappofficial.navigation

sealed class Routes (val routes: String){
    object MainScreen: Routes("main_screen")
    object PrepMeditation: Routes("prep_meditation")
    object DaysScreen: Routes("days_screen")
    object MeditationScreen : Routes("meditation_screen?day={day}&dayNum={dayNum}&dailyRecord={dailyRecord}") {
        fun createRoute(dayNum: Int, day: String, dailyRecord: Int) = "meditation_screen?day=$day&dayNum=$dayNum&dailyRecord=$dailyRecord"
    }
    object TheNewScreen: Routes("pantalla_nueva_route")
}