package com.suprematic.data.local.database.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suprematic.domain.entities.GameTrace

@Entity
data class DbGameTrace(
    @PrimaryKey(autoGenerate = false)
    val timeStamp: Long,
    val gameId: Long,
    val team: DbTeam,
    val pointsScored: Int,
)

fun DbGameTrace.toDomainModel(): GameTrace = GameTrace(
    timeStamp = timeStamp,
    gameId = gameId,
    team = team.toDomainModel(),
    pointsScored = pointsScored
)

fun GameTrace.toDbEntity(): DbGameTrace = DbGameTrace(
    timeStamp = timeStamp,
    gameId = gameId,
    team = team.toDbEntity(),
    pointsScored = pointsScored
)

