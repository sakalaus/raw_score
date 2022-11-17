package com.suprematic.feature.score

import com.suprematic.domain.entities.Game

data class ScoreUiState(
    val isStarted: Boolean = true,
    val isPaused: Boolean = false,
    val game: Game = Game()
)