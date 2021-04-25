package com.example.mad03_fragments_and_navigation.quizend

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mad03_fragments_and_navigation.quizend.QuizEndFragmentArgs
import com.example.mad03_fragments_and_navigation.R
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizEndBinding
import com.example.mad03_fragments_and_navigation.quiz.QuizFragmentDirections


class QuizEndFragment : Fragment() {

    private lateinit var viewModel: QuizEndViewModel
    private lateinit var factory: QuizEndViewModelFactory
    //private var score: Int = 0
    //private var questionsCount = 0

    private lateinit var binding: FragmentQuizEndBinding

    private var endScore: Int = 0
    private var questionsCountXML: Int = 0
    //private val args by navArgs<QuizEndFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("QuizEndFragment", "Called ViewModelProvider.get")

        val argsScore = QuizEndFragmentArgs.fromBundle(requireArguments()).score
        val argsQuestions = QuizEndFragmentArgs.fromBundle(requireArguments()).questionsCount
        factory = QuizEndViewModelFactory(argsScore, argsQuestions)
        viewModel = ViewModelProvider(this, factory).get(QuizEndViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)

        // get score from navigation arguments

        // takes the forwarded argument (selected recipe) and passes it to the viewmodel
        //endScore = args.score
        //questionsCountXML = args.questionsCount

        // show score
        showEndScore()

        binding.btnRestart.setOnClickListener {
            navToQuiz()
        }

        return binding.root
    }

    private fun navToQuiz() {
        // Navigate to Quiz
        requireView().findNavController()
            .navigate(
                QuizEndFragmentDirections.actionQuizEndFragmentToQuizFragment()
            )
    }

    private fun showEndScore() {
        //binding.score = endScore
        //binding.questionsCount = questionsCountXML
        //Log.i("QuizEndFragment", "EndScore: $endScore / $questionsCountXML")

        binding.score = viewModel.vmScore.value
        binding.questionsCount = viewModel.vmQuestionsCount.value
    }

}