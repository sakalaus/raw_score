package com.suprematic.data

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Team
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    override fun initializeGame() {
        TODO("Not yet implemented")
    }

    override fun registeredScoredPoints(game: Game, team: Team, points: Float) {
        TODO("Not yet implemented")
    }

    override fun finalizeGame(game: Game) {
        TODO("Not yet implemented")
    }

    override fun undoLatestEntry(game: Game) {
        TODO("Not yet implemented")
    }

    override fun getGames() {
        TODO("Not yet implemented")
    }

    override fun getGame(gameId: Game) {
        TODO("Not yet implemented")
    }

    override fun getTeam(teamId: Long) {
        TODO("Not yet implemented")
    }

    override fun createTeam(name: String) {
        TODO("Not yet implemented")
    }

}