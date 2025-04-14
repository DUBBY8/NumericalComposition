package com.dubby.numericalcomposition.domain.repository

import com.dubby.numericalcomposition.domain.entity.GameSettings
import com.dubby.numericalcomposition.domain.entity.Level
import com.dubby.numericalcomposition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int,
    ): Question

    fun getGameSettings(level: Level): GameSettings
}