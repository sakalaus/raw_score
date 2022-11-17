package com.suprematic.domain

import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.entities.Team
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun initializeGame()
    fun registeredScoredPoints(game: Game, team: Team, points: Float)
    fun undoLatestEntry(game: Game)
    fun finalizeGame(game: Game)
    suspend fun createTeams(teams: List<Team>)
    suspend fun createSports(sports: List<Sport>)
    fun getGames()
    fun getGame(gameId: Game)
    suspend fun getTeams(): List<Team>
    suspend fun getSports(): List<Sport>
    suspend fun createTeam(name: String)
    fun observeSports(): Flow<List<Sport>>
    fun observeTeams(): Flow<List<Team>>
}