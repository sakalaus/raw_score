package com.suprematic.feature.score

import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.basketball

data class ScoreUiState(
    val isStarted: Boolean = true,
    val game: Game = Game(
        id =1,
        sport = basketball
    )
)