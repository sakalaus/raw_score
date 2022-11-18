package com.suprematic.domain

import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.entities.Team
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getGames()
    suspend fun getGame(gameId: Game)
    suspend fun getGameInProgress(): Game?
    suspend fun getTeams(): List<Team>
    suspend fun getSports(): List<Sport>
    fun observeSports(): Flow<List<Sport>>
    fun observeTeams(): Flow<List<Team>>
    fun observeGame(gameId: Long): Flow<Game>
    suspend fun createTeams(teams: List<Team>)
    suspend fun createSports(sports: List<Sport>)
    suspend fun createGames(games: List<Game>)
    suspend fun toggleGamePaused(game: Game, isPaused: Boolean)
    suspend fun initializeGame(): Game
    suspend fun registeredScoredPoints(game: Game, team: Team, points: Float)
    suspend fun undoLatestEntry(game: Game)
    suspend fun finalizeGame(game: Game)
    suspend fun clearAllGames()
    suspend fun clearAllGameTraces()
}
