package com.example.quiz_app.data

data class Quiz
    (
    val id: String = "",
    val question: String = "",
    val options: List<String> = listOf() ,
    val answer: Int = 0,
){
}

data class User(
    val id: String = "",
    val score: Int = 0,
) {
    // No-argument constructor for Firebase
    constructor() : this("", 0)
}

data class Admin(
    val id: String = "",
    val name: String = "",
)