package com.example.quiz_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.quiz_app.ui.QuizViewModel

@Composable
fun ScoreScreen(viewModel: QuizViewModel, userId: String) {
    val user by viewModel.getUser(userId).observeAsState(initial = null)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        user?.let { user ->
            Text(
                text = "Your Score: ${user.score}",
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

