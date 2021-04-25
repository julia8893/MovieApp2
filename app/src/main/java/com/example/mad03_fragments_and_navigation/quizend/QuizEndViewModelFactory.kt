package com.example.mad03_fragments_and_navigation.quizend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuizEndViewModelFactory(
    private val finalScore: Int,
    private val questionsCount: Int
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizEndViewModel::class.java)) {
            return QuizEndViewModel(finalScore, questionsCount) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}