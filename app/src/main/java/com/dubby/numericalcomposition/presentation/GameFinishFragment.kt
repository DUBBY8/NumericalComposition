package com.dubby.numericalcomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dubby.numericalcomposition.R
import com.dubby.numericalcomposition.databinding.FragmentGameFinishBinding
import com.dubby.numericalcomposition.domain.entity.GameResult

class GameFinishFragment : Fragment() {
    private lateinit var gameResult: GameResult
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun bindViews() {
        with(binding) {
            ivEmojiResult.setImageResource(getRightImage())
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_answers),
                gameResult.gameSettings.minCountOfRightAnswers.toString()
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswers.toString()
            )
            tvRequirePercentage.text = String.format(
                getString(R.string.require_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                calculatePercentOfRightAnswers().toString()
            )
        }
    }

    private fun calculatePercentOfRightAnswers(): Int {
        if (gameResult.countOfQuestions == 0) {
            return 0
        }
        return (gameResult.countOfRightAnswers.toDouble() / gameResult.countOfQuestions * 100).toInt()
    }

    private fun getRightImage(): Int {
        return if (gameResult.winner) {
            R.drawable.good
        } else {
            R.drawable.bad
        }
    }

    private fun setupClickListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })
        binding.btnRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun parseArgs() {
        requireArguments().getParcelable(GAME_RESULT_KEY, GameResult::class.java)?.let {
            gameResult = it
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val GAME_RESULT_KEY = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishFragment {
            return GameFinishFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT_KEY, gameResult)
                }
            }
        }
    }
}