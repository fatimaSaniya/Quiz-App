package com.example.quiz_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.quiz_app.data.Quiz
import com.example.quiz_app.data.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class QuizViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz> get() = _quiz

    // LiveData for quizzes
    val quizzes: LiveData<List<Quiz>> = liveData {
        val snapshot = db.collection("quizzes").get().await()
        val quizzes = snapshot.toObjects(Quiz::class.java)
        emit(quizzes)
    }

    suspend fun addQuiz(quiz: Quiz) {
        db.collection("quizzes").add(quiz).await()
    }

    // Function to get a user
    fun getUser(userId: String): LiveData<User> = liveData {
        val snapshot = db.collection("users").document(userId).get().await()
        val user = snapshot.toObject(User::class.java)
        user?.let { emit(it) }
    }

    // Function to update score
    suspend fun updateScore(userId: String, score: Int) {
        db.collection("users").document(userId).update("score", score).await()
    }
    fun getQuiz(quizId: String): LiveData<Quiz> = liveData {
        val snapshot = db.collection("quizzes").document(quizId).get().await()
        val quiz = snapshot.toObject(Quiz::class.java)
        quiz?.let { emit(it) }
    }

    // Function to update a quiz
    suspend fun updateQuiz(quiz: Quiz) {
        db.collection("quizzes").document(quiz.id).set(quiz).await()
    }

    // Function to delete a quiz
    suspend fun deleteQuiz(quizId: String) {
        db.collection("quizzes").document(quizId).delete().await()
    }
}


