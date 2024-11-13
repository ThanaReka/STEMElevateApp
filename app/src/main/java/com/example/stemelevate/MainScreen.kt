package com.example.stemelevate

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stemelevate.ui.UserPreferencesScreen.UserPreferencesScreen
import com.example.stemelevate.ui.WelcomeScreen


enum class StemScreen(@StringRes val title: Int) {
    Welcome(title = R.string.welcome),
    UserPreferences(title = R.string.user_preferences),
    GeminiFeedbackScreen(title = R.string.gemini_feedback)
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = StemScreen.Welcome.name
    ) {
        composable(route = StemScreen.Welcome.name) {
            WelcomeScreen(navController)
        }
        composable(route = StemScreen.UserPreferences.name) {
            UserPreferencesScreen(navController)
        }
        composable(
            route = "${StemScreen.GeminiFeedbackScreen.name}/{summary}",
            arguments = listOf(navArgument("summary") { type = NavType.StringType })
        ) { backStackEntry ->
            val summary = backStackEntry.arguments?.getString("summary").orEmpty()
            GeminiFeedbackScreen(navController, summary = summary)
        }
    }
}



