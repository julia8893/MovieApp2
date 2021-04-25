package com.example.mad03_fragments_and_navigation.quizend

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizEndViewModel(finalScore: Int, finalQuestions: Int) : ViewModel() {

    // The final score
    //var score: Int = finalScore

    // QuestionsCount
    //var questionsCount: Int = finalQuestions


    private val _vmScore = MutableLiveData<Int>()
    val vmScore: LiveData<Int>
        get() = _vmScore

    private val _vmQuestionsCount = MutableLiveData<Int>()
    val vmQuestionsCount: LiveData<Int>
        get() = _vmQuestionsCount


    init {

        _vmScore.value = finalScore
        _vmQuestionsCount.value = finalQuestions

        Log.i("QuizEndViewModel", "QuizEndViewModel created")
        Log.i("QuizEndViewModel", "Final score is $finalScore")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("QuizEndViewModel", "QuizEndViewModel destroyed!")
    }

}