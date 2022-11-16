package com.suprematic.feature.score

import com.suprematic.domain.entities.Game

data class ScoreUiState(
    val started: Boolean = false,
    val paused: Boolean = false,
    val game: Game = Game()
)