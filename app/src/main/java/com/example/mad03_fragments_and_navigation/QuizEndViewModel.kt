package com.example.mad03_fragments_and_navigation

import android.util.Log
import androidx.lifecycle.ViewModel

class QuizEndViewModel : ViewModel() {

    init {
        Log.i("QuizEndViewModel", "QuizEndViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("QuizEndViewModel", "QuizEndViewModel destroyed!")
    }

}