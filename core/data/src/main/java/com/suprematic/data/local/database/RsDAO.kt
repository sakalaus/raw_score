package com.suprematic.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suprematic.data.local.database.db_entities.DbGame
import com.suprematic.data.local.database.db_entities.DbGameTrace
import com.suprematic.data.local.database.db_entities.DbSport
import com.suprematic.data.local.database.db_entities.DbTeam

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

    @Query("DELETE FROM DbSport")
    suspend fun clearAllSports()

    @Query("DELETE FROM DbTeam")
    suspend fun clearAllTeams()

    @Query("DELETE FROM DbGame")
    suspend fun clearAllGames()

    @Query("DELETE FROM DbGameTrace")
    suspend fun clearAllGameTraces()

}