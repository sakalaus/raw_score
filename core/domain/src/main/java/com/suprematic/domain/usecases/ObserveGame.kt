package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ObserveGame(val repository: Repository) {
    operator fun invoke(game: Game?): Flow<Game?> {
        return if (game == null)
            flowOf(null)
        else
            repository.observeGame(game.id)
    }
}