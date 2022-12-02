package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game

class UpdateGameDuration(val repository: Repository) {
    suspend operator fun invoke(
        game: Game,
    ){
        repository.updateGameDuration(
            gameId = game.id,
            duration = game.duration + 1
        )
    }
}