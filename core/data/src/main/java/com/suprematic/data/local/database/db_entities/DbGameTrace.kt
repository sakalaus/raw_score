package com.suprematic.data.local.database.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suprematic.domain.entities.GameTrace

@Entity
data class DbGameTrace(
    @PrimaryKey(autoGenerate = false)
    val timeStamp: Long,
    val game: DbGame,
    val pointsScoredTeamOne: Int,
    val pointsScoredTeamTwo: Int
)

fun DbGameTrace.toDomainModel(): GameTrace = GameTrace(
    timeStamp = timeStamp,
    game = game.toDomainModel(),
    pointsScoredTeamOne = pointsScoredTeamOne,
    pointsScoredTeamTwo = pointsScoredTeamTwo
)

fun GameTrace.toDbEntity(): DbGameTrace = DbGameTrace(
    timeStamp = timeStamp,
    game = game.toDbEntity(),
    pointsScoredTeamOne = pointsScoredTeamOne,
    pointsScoredTeamTwo = pointsScoredTeamTwo
)

