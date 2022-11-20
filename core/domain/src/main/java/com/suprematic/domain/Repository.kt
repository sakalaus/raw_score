package com.suprematic.domain

import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.GameTrace
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.entities.Team
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getGames(): List<Game>
    suspend fun getGame(gameId: Game): Game
    suspend fun getGameInProgress(): Game?
    suspend fun getTeams(): List<Team>
    suspend fun getSports(): List<Sport>

    fun observeGame(gameId: Long): Flow<Game>
    fun observeGames(): Flow<List<Game>>
    fun observeSports(): Flow<List<Sport>>
    fun observeTeams(): Flow<List<Team>>

    suspend fun createTeams(teams: List<Team>)
    suspend fun createSports(sports: List<Sport>)
    suspend fun createGames(games: List<Game>)

    suspend fun initializeGame(): Game
    suspend fun toggleGamePaused(game: Game, isPaused: Boolean)
    suspend fun registerPointsInTrace(gameTrace: GameTrace)
    suspend fun undoLastGameTraceEntry(game: Game)
    suspend fun updatePointsInGame(game: Game, team: Team, points: Int, firstTeamScored: Boolean)
    suspend fun calculatePointsByGameTrace(game: Game, team: Team): Int
    suspend fun finalizeGame(game: Game)

    suspend fun clearAllGames()
    suspend fun clearAllGameTraces()
}
