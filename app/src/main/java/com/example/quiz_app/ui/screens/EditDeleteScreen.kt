package com.example.quiz_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.quiz_app.data.Quiz
import com.example.quiz_app.ui.QuizViewModel
import kotlinx.coroutines.launch

@Composable
fun EditDeleteScreen(viewModel: QuizViewModel, quizId: String, navController: NavController) {
    val quiz by viewModel.getQuiz(quizId).asFlow().collectAsState(initial = null)

    var question by remember { mutableStateOf(quiz?.question ?: "") }
    var option1 by remember { mutableStateOf(quiz?.options?.getOrNull(0) ?: "") }
    var option2 by remember { mutableStateOf(quiz?.options?.getOrNull(1) ?: "") }
    var option3 by remember { mutableStateOf(quiz?.options?.getOrNull(2) ?: "") }
    var option4 by remember { mutableStateOf(quiz?.options?.getOrNull(3) ?: "") }
    var answer by remember { mutableStateOf(quiz?.answer?.toString() ?: "") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.White)
    ) {
        LaunchedEffect(quiz) {
            question = quiz?.question ?: ""
            option1 = quiz?.options?.getOrNull(0) ?: ""
            option2 = quiz?.options?.getOrNull(1) ?: ""
            option3 = quiz?.options?.getOrNull(2) ?: ""
            option4 = quiz?.options?.getOrNull(3) ?: ""
            answer = quiz?.answer?.toString() ?: ""
        }


        OutlinedTextField(
            value = question,
            onValueChange = { question = it },
            label = { Text("Question") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = option1,
            onValueChange = { option1 = it },
            label = { Text("Option 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = option2,
            onValueChange = { option2 = it },
            label = { Text("Option 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = option3,
            onValueChange = { option3 = it },
            label = { Text("Option 3") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = option4,
            onValueChange = { option4 = it },
            label = { Text("Option 4") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = answer,
            onValueChange = { answer = it },
            label = { Text("Answer") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = {
            val updatedQuiz = Quiz(
                id = quizId,
                question = question,
                options = listOf(option1, option2, option3, option4),
                answer = answer.toInt()
            )
            viewModel.viewModelScope.launch {
                viewModel.updateQuiz(updatedQuiz)
            }
        }) {
            Text("Update Quiz")
        }

        Button(onClick = {
            viewModel.viewModelScope.launch {
                viewModel.deleteQuiz(quizId)
            }
            navController.popBackStack()
        }) {
            Text("Delete Quiz")
        }
    }
}
