package com.suprematic.feature.score

import com.suprematic.domain.entities.Game

data class ScoreUiState(
    val isGameInitialized: Boolean = false,
    val game: Game? = null
){
    val isGameActive: Boolean
    get() = (game != null) && game.isInProgress && !game.isPaused
}