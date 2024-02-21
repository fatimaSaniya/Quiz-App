package com.example.quiz_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.quiz_app.data.Quiz
import com.example.quiz_app.ui.QuizViewModel
import kotlinx.coroutines.launch

@Composable
fun AdminScreen(viewModel: QuizViewModel, navController: NavController) {
    var id by remember { mutableStateOf("") }
    var question by remember { mutableStateOf("") }
    var option1 by remember { mutableStateOf("") }
    var option2 by remember { mutableStateOf("") }
    var option3 by remember { mutableStateOf("") }
    var option4 by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }

    Column(
      //  verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.White)
    ) {
        Text(
            text = "Add question",
            fontSize = 32.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(start = 99.dp, top = 6.dp, end =96.dp ),
            textDecoration = TextDecoration.Underline)
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("Question Id") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
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
            val quiz = Quiz(
                id = id ,
                question = question,
                options = listOf(option1, option2, option3, option4),
                answer = answer.toInt()
            )
            viewModel.viewModelScope.launch {
                viewModel.addQuiz(quiz)
            }
        }) {
            Text("Add Quiz")
        }
        var quizId by remember { mutableStateOf("") }

        OutlinedTextField(
            value = quizId,
            onValueChange = { quizId = it },
            label = { Text("Quiz ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Button(onClick = {
            navController.navigate("editDeleteScreen/$quizId")
        }) {
            Text("Edit/Delete Quiz")
        }
    }
}
