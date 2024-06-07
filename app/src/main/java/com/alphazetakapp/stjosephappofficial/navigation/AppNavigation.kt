package com.alphazetakapp.stjosephappofficial.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alphazetakapp.stjosephappofficial.ReadMeditationScreen.MeditationScreen
import com.alphazetakapp.stjosephappofficial.ReadMeditationScreen.TheNewScreen
import com.alphazetakapp.stjosephappofficial.SelectedDay.DaysScreen
import com.alphazetakapp.stjosephappofficial.ui.MainScreen
import com.alphazetakapp.stjosephappofficial.ui.PrepMeditation

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = Routes.MainScreen.routes
    ) {
        composable(Routes.MainScreen.routes) {
            val context = LocalContext.current
            MainScreen(navigationController, context = context)
        }
        composable(Routes.PrepMeditation.routes) {
            PrepMeditation(navigationController)
        }
        composable(Routes.DaysScreen.routes) {
            DaysScreen(navigationController)
        }
        composable(
            Routes.MeditationScreen.routes,
            arguments = listOf(
                navArgument("day") { defaultValue = "Day X" },
                navArgument("dayNum") { defaultValue = 0 },
                navArgument("dailyRecord") { defaultValue = 0 })
        ) { backStackEntry ->
            val day = backStackEntry.arguments?.getString("day") ?: "Day X"
            val dayNum = backStackEntry.arguments?.getInt("dayNum") ?: 0
            val dailyRecord = backStackEntry.arguments?.getInt("dailyRecord") ?: 0
            val context = LocalContext.current

            if (dayNum == 31) {
                TheNewScreen()
            } else {
                MeditationScreen(
                    dayNum = dayNum,
                    day = day,
                    dailyRecord = dailyRecord,
                    context = context
                )
            }
        }
    }
}