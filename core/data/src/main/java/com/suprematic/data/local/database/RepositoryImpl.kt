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

    override fun initializeGame() {
        TODO("Not yet implemented")
    }

    override fun registeredScoredPoints(game: Game, team: Team, points: Float) {
        TODO("Not yet implemented")
    }

    override fun finalizeGame(game: Game) {
        TODO("Not yet implemented")
    }

    override suspend fun createTeams(teams: List<Team>) {
        dao.insertTeams(teams.map { it.toDbEntity() })
    }

    override suspend fun createSports(sports: List<Sport>) {
        dao.insertSports(sports.map { it.toDbEntity() })
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

    override suspend fun getTeams(): List<Team> {
        return dao.getTeams().map { it.toDomainModel() }
    }

    override suspend fun getSports(): List<Sport> {
        return dao.getSports().map { it.toDomainModel() }
    }

    override suspend fun createTeam(name: String) {
        TODO("Not yet implemented")
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

}