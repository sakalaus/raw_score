package com.suprematic.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suprematic.data.local.database.db_entities.DbGame
import com.suprematic.data.local.database.db_entities.DbGameTrace
import com.suprematic.data.local.database.db_entities.DbSport
import com.suprematic.data.local.database.db_entities.DbTeam
import com.suprematic.domain.entities.Sport
import kotlinx.coroutines.flow.Flow

@Dao
interface RsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSports(sports: List<DbSport>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<DbTeam>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<DbGame>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameTraces(gameTraces: List<DbGameTrace>)

    @Query("SELECT * FROM DbSport ORDER BY name ASC")
    fun observeSports(): Flow<List<DbSport>>

    @Query("SELECT * FROM DbSport ORDER BY name ASC")
    suspend fun getSports(): List<DbSport>

    @Query("SELECT * FROM DbTeam ORDER BY name ASC")
    fun observeTeams(): Flow<List<DbTeam>>

    @Query("SELECT * FROM DbTeam ORDER BY name ASC")
    suspend fun getTeams(): List<DbTeam>

    @Query("SELECT * FROM DbGame ORDER BY timeStamp DESC")
    fun observeGames(): Flow<List<DbGame>>

    @Query("SELECT * FROM DbGame ORDER BY timeStamp DESC")
    suspend fun getGames(): List<DbGame>

    @Query("SELECT * FROM DbGame WHERE isInProgress LIMIT 1")
    suspend fun getGameInProgress(): DbGame

    @Query("SELECT * FROM DbGame WHERE id = :gameId")
    fun observeGame(gameId: Long): Flow<DbGame>

    @Query("Update DbGame SET isPaused = :isPaused WHERE id = :gameId")
    suspend fun toggleGamePaused(gameId: Long, isPaused: Boolean)

//    @Query("SELECT SUM(pointsScoredTeamOne), SUM(pointsScoredTeamTwo) FROM DbGameTrace WHERE game = :game GROUP BY game")
//    fun observePointsByGameTrace(game: DbGame, team: DbTeam): Flow<DbGameTrace>

    @Query("DELETE FROM DbGameTrace WHERE game = :game " +
            "AND timeStamp IN (SELECT MAX(timeStamp) FROM DbGameTrace WHERE game = :game)")
    suspend fun deleteLastTraceEntry(game: DbGame)

    @Query("DELETE FROM DbSport")
    suspend fun clearAllSports()

    @Query("DELETE FROM DbTeam")
    suspend fun clearAllTeams()

    @Query("DELETE FROM DbGame")
    suspend fun clearAllGames()

    @Query("DELETE FROM DbGameTrace")
    suspend fun clearAllGameTraces()

}