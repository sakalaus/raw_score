package com.suprematic.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suprematic.data.converters.GameTypeConverter
import com.suprematic.data.local.database.db_entities.DbGame
import com.suprematic.data.local.database.db_entities.DbGameTrace
import com.suprematic.data.local.database.db_entities.DbSport
import com.suprematic.data.local.database.db_entities.DbTeam

@Database(
    entities = [DbGame::class, DbGameTrace::class, DbSport::class, DbTeam::class],
    version = 3
)
@TypeConverters(
    GameTypeConverter::class
)
abstract class RsDataBase: RoomDatabase() {
    abstract val dao: RsDAO
    companion object {
        const val DATABASE_NAME = "raw_score.db"
    }
}