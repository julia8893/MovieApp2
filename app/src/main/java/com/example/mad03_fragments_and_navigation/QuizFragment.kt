package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizBinding
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue


class QuizFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuizBinding
    //private val questions =
    //    QuestionCatalogue().defaultQuestions    // get a list of questions for the game
    //private var score = 0                                           // save the user's score
    //private var index = 0                                           // index for question data to show
    //private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.i("QuizFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        // shuffle array
        //questions.shuffle()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)

        binding.quizViewModel = viewModel
        binding.lifecycleOwner = this

        //binding.index = index
        //binding.questionsCount = questions.size

        //binding.question = questions[index]

        //setQuestionProgress()

        /*
        binding.btnNext.setOnClickListener {
            nextQuestion()
        }
         */


        viewModel.btnNext.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                viewModel.nextQuestion()
            }
        })


        return binding.root
    }

    /*
    private fun nextQuestion() {
        // get selected answer
        // check if is correct answer
        // update score
        // check if there are any questions left
        // show next question OR
        // navigate to QuizEndFragment

        // @Question 1, 2
        if (index < numQuestions) {

            if (checkIfNotEmpty() == true) {
                setNextQuestion()
            }
        }

        // @Question 3
        else {
            if (checkIfNotEmpty() == true) {
                navToQuizEnd()
            }
        }
    }

    private fun getIndexRadioBtn(): Int? {
        return when (binding.answerBox.checkedRadioButtonId) {
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


    private fun setNextQuestion() {

        //val arraySize = questions.size
        index++
        binding.question = questions[index]

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


     */
}