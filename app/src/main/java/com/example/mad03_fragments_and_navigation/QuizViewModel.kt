package com.example.mad03_fragments_and_navigation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mad03_fragments_and_navigation.models.Question
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue

class QuizViewModel : ViewModel() {

    private val questions: List<Question>

    private var score: Int
    //private var index: Int


    private val _btnNext = MutableLiveData<Boolean>()
    val btnNext: LiveData<Boolean>
        get() = _btnNext

    private val _vmQuestion = MutableLiveData<Question>()
    val vmQuestion: LiveData<Question>
        get() = _vmQuestion

    private val _vmIndex = MutableLiveData<Int>()
    val vmIndex: LiveData<Int>
        get() = _vmIndex

    private val _vmQuestionsCount = MutableLiveData<Int>()
    val vmQuestionsCount: LiveData<Int>
        get() = _vmQuestionsCount


    init {
        Log.i("QuizViewModel", "QuizViewModel created")

        _btnNext.value = false
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


    fun btnNextClicked() {
        _btnNext.value = true
        Log.i("QuizViewModel", "btn pressed")
    }


    fun nextQuestion() {
        // get selected answer
        // check if is correct answer
        // update score
        // check if there are any questions left
        // show next question OR
        // navigate to QuizEndFragment

        // @Question 1, 2
        if (_vmIndex.value!! < _vmQuestionsCount.value!!) {

            if (checkIfNotEmpty() == true) {
                //setNextQuestion()
            }
        }

        // @Question 3
        else {
            if (checkIfNotEmpty() == true) {
                navToQuizEnd()
            }
        }
    }


    private fun checkIfNotEmpty(): Boolean? {

        var bool: Boolean

        if (getIndexRadioBtn() != null) {

            bool = true
            checkIfCorrect()

        } else {
            bool = false
        }
        return bool
    }

    fun test(){

    }

    private fun getIndexRadioBtn(): Int? {
        return when (answerBox.checkedRadioButtonId) {
            R.id.answer1 -> 0
            R.id.answer2 -> 1
            R.id.answer3 -> 2
            R.id.answer4 -> 3
            else -> {
                Toast.makeText(
                    requireContext(),
                    "Keine Antwort ausgewählt",
                    Toast.LENGTH_LONG
                )
                    .show()
                null
            }
        }
    }


    private fun setNextQuestion() {

        //val arraySize = questions.size
        index++

            .question = questions[index]

        resetRadioButtons()
        setQuestionProgress()
    }

    private fun setQuestionProgress() {
        binding.index = (index + 1)
        binding.questionsCount = questions.size
        Log.i("QuizFragment", "Question ${index + 1} / ${questions.size}")
    }


    private fun checkIfCorrect() {

        if (getIndexRadioBtn()?.let { isCorrect(it) }!!) {
            increaseScore()
        }
    }

    private fun isCorrect(id: Int): Boolean {
        return questions[index].answers[id].isCorrectAnswer
    }

    private fun increaseScore() {
        // Score++
        score++
        Log.i("QuizFragment", "Score: $score")
    }

    private fun navToQuizEnd() {
        // Navigate to QuizEnd
        requireView().findNavController()
            .navigate(
                QuizFragmentDirections.actionQuizFragmentToQuizEndFragment(
                    score,
                    questions.size
                )
            )
    }

    private fun resetRadioButtons() {
        binding.answerBox.clearCheck()
    }


    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        index = 0
        setNextQuestion()
    }

}

