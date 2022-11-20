package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.GameTrace
import javax.inject.Inject

class UndoLastGameTraceEntry @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(game: Game) {
        repository.undoLastGameTraceEntry(
            game = game
        )
        game.teamOne?.let{ team ->
            repository.updatePointsInGame(
                game = game,
                team = team,
                points = repository.calculatePointsByGameTrace(
                    game = game,
                    team = team
                ),
                firstTeamScored = true
            )
        }
        game.teamTwo?.let{ team ->
            repository.updatePointsInGame(
                game = game,
                team = team,
                points = repository.calculatePointsByGameTrace(
                    game = game,
                    team = team
                ),
                firstTeamScored = false
            )
        }
    }
}