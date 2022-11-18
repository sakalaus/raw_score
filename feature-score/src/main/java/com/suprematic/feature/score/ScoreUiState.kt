package com.suprematic.feature.score

import com.suprematic.domain.entities.Game

data class ScoreUiState(
    val game: Game? = null
){
    val isGameInitialized
    get() = game != null
}