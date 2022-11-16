package com.suprematic.domain

import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Team

interface Repository {
    fun initializeGame()
    fun registeredScoredPoints(game: Game, team: Team, points: Float)
    fun undoLatestEntry(game: Game)
    fun finalizeGame(game: Game)
    fun getGames()
    fun getGame(gameId: Game)
    fun getTeam(teamId: Long)
    fun createTeam(name: String)
}