package com.alphazetakapp.stjosephappofficial.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alphazetakapp.stjosephappofficial.ReadMeditationScreen.TheNewScreen
import com.alphazetakapp.stjosephappofficial.presentation.meditation.detail.MeditationScreen
import com.alphazetakapp.stjosephappofficial.presentation.meditation.list.DaysScreen
import com.alphazetakapp.stjosephappofficial.presentation.preparation.PrepScreen
import com.alphazetakapp.stjosephappofficial.presentation.welcome.WelcomeScreen

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = Screen.Welcome.route,
        modifier = modifier
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navigationController, viewModel = hiltViewModel())
        }
        composable(Screen.Preparation.route) {
            PrepScreen(navigationController, viewModel = hiltViewModel())
        }
        composable(Screen.Days.route) {
            DaysScreen(
                navController = navigationController,
                viewModel = hiltViewModel()
            )
        }
        composable(
            route = Screen.MeditationNav.route,
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
                    viewModel = hiltViewModel()
                )
            }
        }
    }
}