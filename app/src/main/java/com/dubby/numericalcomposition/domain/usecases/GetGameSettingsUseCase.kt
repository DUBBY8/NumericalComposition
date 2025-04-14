package com.dubby.numericalcomposition.domain.usecases

import com.dubby.numericalcomposition.domain.entity.GameSettings
import com.dubby.numericalcomposition.domain.entity.Level
import com.dubby.numericalcomposition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}