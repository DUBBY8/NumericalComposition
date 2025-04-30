package com.dubby.numericalcomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dubby.numericalcomposition.R
import com.dubby.numericalcomposition.databinding.FragmentGameFinishBinding

class GameFinishFragment : Fragment() {
    private val args by navArgs<GameFinishFragmentArgs>()
    private var _binding: FragmentGameFinishBinding? = null
    private val binding: FragmentGameFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            ivEmojiResult.setImageResource(getRightImage())
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_answers),
                args.gameResult.gameSettings.minCountOfRightAnswers.toString()
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                args.gameResult.countOfRightAnswers.toString()
            )
            tvRequirePercentage.text = String.format(
                getString(R.string.require_percentage),
                args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                calculatePercentOfRightAnswers().toString()
            )
        }
    }

    private fun calculatePercentOfRightAnswers(): Int {
        if (args.gameResult.countOfQuestions == 0) {
            return 0
        }
        return (args.gameResult.countOfRightAnswers.toDouble() / args.gameResult.countOfQuestions * 100).toInt()
    }

    private fun getRightImage(): Int {
        return if (args.gameResult.winner) {
            R.drawable.good
        } else {
            R.drawable.bad
        }
    }

    private fun setupClickListeners() {
        binding.btnRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}