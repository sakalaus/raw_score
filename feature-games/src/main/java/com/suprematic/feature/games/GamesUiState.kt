package com.suprematic.feature.games

import com.suprematic.domain.entities.Game

data class GamesUiState(
    val games: Map<Game, String> = emptyMap()
) {
    val noGamesFound: Boolean
        get() = games.isEmpty()
}