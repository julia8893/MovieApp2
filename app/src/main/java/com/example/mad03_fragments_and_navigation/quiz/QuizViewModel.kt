package com.example.mad03_fragments_and_navigation.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mad03_fragments_and_navigation.models.Question
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue

class QuizViewModel : ViewModel() {


    val questions: List<Question>

    var score: Int
    //private var index: Int

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message


    private val _vmQuestion = MutableLiveData<Question>()
    val vmQuestion: LiveData<Question>
        get() = _vmQuestion

    private val _vmIndex = MutableLiveData<Int>()
    val vmIndex: LiveData<Int>
        get() = _vmIndex

    private val _vmQuestionsCount = MutableLiveData<Int>()
    val vmQuestionsCount: LiveData<Int>
        get() = _vmQuestionsCount

    private val _vmSelectedId = MutableLiveData<Int>()
    val vmSelectedId: LiveData<Int>
        get() = _vmSelectedId

    private val _vmQuizEnd = MutableLiveData<Boolean>()
    val vmQuizEnd: LiveData<Boolean>
        get() = _vmQuizEnd


    init {
        Log.i("QuizViewModel", "QuizViewModel created")

        _vmQuizEnd.value = false
        score = 0       // save the user's score
        _vmIndex.value = 0       // index for question data to show
        questions = QuestionCatalogue().defaultQuestions    // get a list of questions for the game
        _vmQuestionsCount.value = questions.size //Math.min((questions.size + 1) / 2, 3)

        // shuffle array
        questions.shuffle()

        // take first question of list and set as current question
        _vmQuestion.value = questions[vmIndex.value!!]
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel", "QuizViewModel destroyed!")
    }

    fun vmBtnNextClicked(int: Int) {

        _vmSelectedId.value = int

        if (isCorrect()) {
            increaseScore()
        }

        vmNextQuestion()
    }


    private fun vmNextQuestion() {
        // get selected answer
        // check if is correct answer
        // update score
        // check if there are any questions left
        // show next question OR
        // navigate to QuizEndFragment

        // @Question 1, 2
        if (_vmIndex.value!! < _vmQuestionsCount.value!!.minus(1)) {

            setNextQuestion()
        }

        // @Question 3
        else {
            _vmQuizEnd.value = true
        }
    }


    private fun isCorrect(): Boolean {
        return questions[_vmIndex.value!!].answers[_vmSelectedId.value!!].isCorrectAnswer
    }

    private fun increaseScore() {
        // Score++
        score++
        Log.i("QuizFragment", "Score: $score")
    }


    private fun setNextQuestion() {

        //val arraySize = questions.size
        _vmIndex.value = _vmIndex.value?.plus(1)

        // Sets next question
        _vmQuestion.value = questions[_vmIndex.value!!]

    }

}