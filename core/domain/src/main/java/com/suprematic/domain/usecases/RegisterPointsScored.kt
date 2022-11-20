package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.GameTrace
import com.suprematic.domain.entities.Team
import javax.inject.Inject

class RegisterPointsScored @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(game: Game, team: Team, pointsScored: Int) {
        repository.registerPointsInTrace(
            GameTrace(
                timeStamp = System.currentTimeMillis(),
                gameId = game.id,
                team = team,
                pointsScored = pointsScored
            )
        )
        repository.updatePointsInGame(
            game = game,
            team = team,
            points = repository.calculatePointsByGameTrace(
                game = game,
                team = team
            ),
            firstTeamScored = (game.teamOne == team)
        )
    }
}