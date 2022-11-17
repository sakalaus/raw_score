package com.suprematic.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.suprematic.data.local.database.db_entities.DbGame
import com.suprematic.data.local.database.db_entities.DbSport
import com.suprematic.data.local.database.db_entities.DbTeam
import javax.inject.Inject


@ProvidedTypeConverter
class GameTypeConverter @Inject constructor(
    private val jsonConverter: JsonConverter
)  {

    @TypeConverter
    fun fromTeam(category: DbTeam): String {
        return jsonConverter.toJson(category)
    }

    @TypeConverter
    fun toTeam(json: String): DbTeam? {
        return (jsonConverter.fromJson(json))
    }

    @TypeConverter
    fun fromSport(category: DbSport): String {
        return jsonConverter.toJson(category)
    }

    @TypeConverter
    fun toSport(json: String): DbSport? {
        return (jsonConverter.fromJson(json))
    }

    @TypeConverter
    fun fromGame(category: DbGame): String {
        return jsonConverter.toJson(category)
    }

    @TypeConverter
    fun toGame(json: String): DbGame? {
        return (jsonConverter.fromJson(json))
    }
}