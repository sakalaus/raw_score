package com.suprematic.feature.games

import com.suprematic.domain.entities.Game

data class GamesUiState(
    val games: List<Game> = emptyList()
) {
    val noGamesFound: Boolean
        get() = games.isEmpty()
}