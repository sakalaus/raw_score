package com.suprematic.data.local.database

import com.suprematic.data.local.database.db_entities.toDbEntity
import com.suprematic.data.local.database.db_entities.toDomainModel
import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.GameTrace
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.entities.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    db: RsDataBase
) : Repository {

    private val dao = db.dao

    override suspend fun initializeGame(): Game {
        TODO("Not yet implemented")
    }

    override suspend fun finalizeGame(game: Game) {
        dao.finalizeGame(gameId = game.id)
    }

    override suspend fun registerPointsInTrace(gameTrace: GameTrace) {
        dao.insertGameTraces(listOf(gameTrace).map { it.toDbEntity() })
    }

    override suspend fun updatePointsInGame(game: Game, team: Team, points: Int, firstTeamScored: Boolean) {
        if (firstTeamScored)
            dao.updatePointsTeamOneInGame(gameId = game.id, points = points)
        else
            dao.updatePointsTeamTwoInGame(gameId = game.id, points = points)
    }

    override suspend fun calculatePointsByGameTrace(game: Game, team: Team): Int {
        return dao.calculatePointsByGameTrace(gameId = game.id, team = team.toDbEntity())
    }

    override suspend fun createTeams(teams: List<Team>) {
        dao.insertTeams(teams.map { it.toDbEntity() })
    }

    override suspend fun createSports(sports: List<Sport>) {
        dao.insertSports(sports.map { it.toDbEntity() })
    }

    override suspend fun undoLastGameTraceEntry(game: Game) {
       dao.deleteLastTraceEntry(gameId = game.id)
    }

    override suspend fun getGames(): List<Game> {
        return dao.getGames().map { it.toDomainModel() }
    }

    override suspend fun getGame(game: Game): Game {
        return dao.getGame(gameId = game.id).toDomainModel()
    }

    override suspend fun getGameInProgress(): Game? {
        return dao.getGameInProgress()?.toDomainModel()
    }

    override suspend fun getTeams(): List<Team> {
        return dao.getTeams().map { it.toDomainModel() }
    }

    override suspend fun getSports(): List<Sport> {
        return dao.getSports().map { it.toDomainModel() }
    }

    override suspend fun createGames(games: List<Game>) {
        dao.insertGames(games.map { it.toDbEntity() })
    }

    override suspend fun toggleGamePaused(game: Game, isPaused: Boolean) {
        dao.toggleGamePaused(gameId = game.id, isPaused = isPaused)
    }

    override fun observeSports(): Flow<List<Sport>> {
        return dao.observeSports().map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override fun observeTeams(): Flow<List<Team>> {
        return dao.observeTeams().map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override fun observeGame(gameId: Long): Flow<Game> {
        return dao.observeGame(gameId).map { it.toDomainModel() }
    }

    override fun observeGames(): Flow<List<Game>> {
        return dao.observeGames().map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override suspend fun clearAllGames() {
        dao.clearAllGames()
    }

    override suspend fun clearAllGameTraces() {
        dao.clearAllGameTraces()
    }

}