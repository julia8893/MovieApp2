package com.example.mad03_fragments_and_navigation.quiz

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mad03_fragments_and_navigation.models.Question
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue

/**
 * ViewModel containing all the logic needed to run the game
 */
class QuizViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L

        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    val questions: List<Question>

    var score: Int
    //private var index: Int

    // The current question
    private val _vmQuestion = MutableLiveData<Question>()
    val vmQuestion: LiveData<Question>
        get() = _vmQuestion

    // The current index of the current question
    private val _vmIndex = MutableLiveData<Int>()
    val vmIndex: LiveData<Int>
        get() = _vmIndex

    // The total number of questions
    private val _vmQuestionsCount = MutableLiveData<Int>()
    val vmQuestionsCount: LiveData<Int>
        get() = _vmQuestionsCount

    private val _vmSelectedId = MutableLiveData<Int>()
    val vmSelectedId: LiveData<Int>
        get() = _vmSelectedId

    // Trigger value for quiz end
    private val _vmQuizEnd = MutableLiveData<Boolean>()
    val vmQuizEnd: LiveData<Boolean>
        get() = _vmQuizEnd

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message


    init {

        Log.i("QuizViewModel", "QuizViewModel created")

        // Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                // what should happen each tick of the timer
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                // what should happen when the timer finishes
                _currentTime.value = DONE
                _vmQuizEnd.value = true
            }
        }

        timer.start()

        _vmQuizEnd.value = false
        score = 0                // save the user's score
        _vmIndex.value = 0       // index for question data to show
        questions = QuestionCatalogue().defaultQuestions    // get a list of questions for the game
        _vmQuestionsCount.value = questions.size            //Math.min((questions.size + 1) / 2, 3)

        // shuffle array
        questions.shuffle()

        // take first question of list and set as current question
        _vmQuestion.value = questions[vmIndex.value!!]
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

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("QuizViewModel", "QuizViewModel destroyed!")
    }

}