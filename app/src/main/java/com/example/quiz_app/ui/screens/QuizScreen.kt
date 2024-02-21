package com.example.quiz_app.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quiz_app.ui.QuizViewModel
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(viewModel: QuizViewModel, navController: NavController) {
    val quizzes by viewModel.quizzes.observeAsState(initial = emptyList())
    val userId = "currentUserId"
    val user by viewModel.getUser(userId).observeAsState(initial = null)
    val scope = rememberCoroutineScope()

    // Display quizzes and user data
    // TODO: Implement your UI here

    // Navigate to admin screen
    Box(
        modifier = Modifier.background(Color.hsl(26f, 0.1f, 0.86f))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Text(
                    text = "Quizzes",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.padding(top = 6.dp, end =96.dp ),
                    textDecoration = TextDecoration.Underline)
            FilledTonalButton(
                onClick = { navController.navigate("adminScreen") },
                colors = ButtonDefaults.buttonColors(Color.hsl(240f, 0.9f, 0.79f))) {
                Text("Go to Admin Screen",
                    modifier = Modifier.padding(8.dp),)
            }}
            Card(
                modifier = Modifier
                    .background(Color.hsl(265f, 1f, 0.86f))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .background(Color.hsl(26f, 0.1f, 0.86f))
                        .padding(top = 18.dp, start = 3.dp, bottom = 8.dp),

                ) {

                    items(quizzes) { quiz ->
                        Text(
                            text = quiz.question,
                            color = Color.Black,
                            fontSize = 23.sp,
                            modifier = Modifier.padding(start = 16.dp))

                        var selectedOption by remember { mutableStateOf(-1) }

                        Column {
                            quiz.options.forEachIndexed { index, option ->
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .selectable(
                                            selected = (selectedOption == index),
                                            onClick = { selectedOption = index }
                                        )
                                ) {
                                    RadioButton(
                                        selected = (selectedOption == index),
                                        onClick = { selectedOption = index }
                                    )
                                    Text(
                                        text = "$index: $option",
                                        color = Color.Black,
                                        fontSize = 17.sp,
                                        modifier = Modifier.padding(top = 16.dp)
                                    )
                                }
                            }
                        }

                        if (user != null) {
                            FilledTonalButton(onClick = {
                                scope.launch {
                                    viewModel.updateScore(user!!.id, user!!.score + 1)
                                    navController.navigate("scoreScreen/${user!!.id}")
                                }
                                if (selectedOption == quiz.answer) {
                                    scope.launch {
                                        viewModel.updateScore(user!!.id, user!!.score + 1)
                                    }
                                }
                                selectedOption = -1
                            },
                                modifier = Modifier.fillMaxWidth(1f),
                                colors = ButtonDefaults.buttonColors(Color.hsl(240f, 0.9f, 0.79f))) {
                                Text("Submit and view score 😊")
                            }
                        } else {
                            Text("Loading user data...")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

