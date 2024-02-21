package com.example.quiz_app.ui.screens

import com.example.quiz_app.data.Admin
import com.example.quiz_app.data.Quiz

class AdminSection(
    private val admin: Admin
) {
    private val quizzes = mutableListOf<Quiz>()

    fun addQuiz(quiz: Quiz) {
        quizzes.add(quiz)
        println("Quiz with id ${quiz.id} added by admin ${admin.name}")
    }

    fun viewQuizzes() {
        quizzes.forEach { quiz ->
            println("Quiz ID: ${quiz.id}, Question: ${quiz.question}, Options: ${quiz.options}, Answer: ${quiz.answer}")
        }
    }
}