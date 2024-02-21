package com.example.quiz_app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz_app.ui.screens.AdminScreen
import com.example.quiz_app.ui.screens.EditDeleteScreen
import com.example.quiz_app.ui.screens.QuizScreen
import com.example.quiz_app.ui.screens.ScoreScreen

@Composable
fun Navigation(quizViewModel: QuizViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "quizScreen") {
        composable("quizScreen") { QuizScreen(quizViewModel, navController) }

        composable("scoreScreen/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            if (userId != null) {
                ScoreScreen(viewModel = quizViewModel, userId = userId)
            }
        }

        composable("adminScreen") { AdminScreen(quizViewModel, navController) }
        composable("editDeleteScreen/{quizId}") { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString("quizId")
            if (quizId != null) {
                EditDeleteScreen(viewModel = QuizViewModel(), quizId = quizId, navController = navController)
            }
        }
    }
}
