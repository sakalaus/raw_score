package com.suprematic.data.local.database

import com.suprematic.data.local.database.db_entities.toDbEntity
import com.suprematic.data.local.database.db_entities.toDomainModel
import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
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

    override suspend fun registeredScoredPoints(game: Game, team: Team, points: Float) {
        TODO("Not yet implemented")
    }

    override suspend fun finalizeGame(game: Game) {
        TODO("Not yet implemented")
    }

    override suspend fun createTeams(teams: List<Team>) {
        dao.insertTeams(teams.map { it.toDbEntity() })
    }

    override suspend fun createSports(sports: List<Sport>) {
        dao.insertSports(sports.map { it.toDbEntity() })
    }

    override suspend fun undoLatestEntry(game: Game) {
        TODO("Not yet implemented")
    }

    override suspend fun getGames() {
        TODO("Not yet implemented")
    }

    override suspend fun getGame(gameId: Game) {
        TODO("Not yet implemented")
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

    override fun observeSports(): Flow<List<Sport>>
    {
        return dao.observeSports().map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override fun observeTeams(): Flow<List<Team>>
    {
        return dao.observeTeams().map { list ->
            list.map { it.toDomainModel() }
        }
    }

    override fun observeGame(gameId: Long): Flow<Game>
    {
        return dao.observeGame(gameId).map {it.toDomainModel()}
    }
    override suspend fun clearAllGames() {
        dao.clearAllGames()
    }

    override suspend fun clearAllGameTraces() {
        dao.clearAllGameTraces()
    }

}