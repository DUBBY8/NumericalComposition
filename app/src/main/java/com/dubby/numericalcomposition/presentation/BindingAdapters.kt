package com.dubby.numericalcomposition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dubby.numericalcomposition.R
import com.dubby.numericalcomposition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers),
        count.toString()
    )
}

@BindingAdapter("score")
fun bindScore(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        score.toString()
    )
}

@BindingAdapter("requirePercentage")
fun bindRequiredPercent(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.require_percentage),
        count.toString()
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.require_percentage),
        calculatePercentOfRightAnswers(gameResult).toString()
    )
}

private fun calculatePercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    }
    (countOfRightAnswers.toDouble() / countOfQuestions * 100).toInt()
}


@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getRightImage(winner))
}

private fun getRightImage(winner: Boolean): Int {
    return if (winner) {
        R.drawable.good
    } else {
        R.drawable.bad
    }
}