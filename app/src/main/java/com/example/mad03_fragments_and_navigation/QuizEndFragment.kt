package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizEndBinding


class QuizEndFragment : Fragment() {

    private lateinit var viewModel: QuizEndViewModel
    private lateinit var binding: FragmentQuizEndBinding

    private var endScore: Int = 0
    private var questionsCountXML: Int = 0
    private val args by navArgs<QuizEndFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("QuizEndFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(QuizEndViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)

        // get score from navigation arguments

        // takes the forwarded argument (selected recipe) and passes it to the viewmodel
        endScore = args.score
        questionsCountXML = args.questionsCount

        // show score
        showEndScore()

        return binding.root
    }

    private fun showEndScore(){
        binding.score = endScore
        binding.questionsCount = questionsCountXML
        Log.i("QuizEndFragment","EndScore: $endScore / $questionsCountXML")
    }
}