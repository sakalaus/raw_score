package com.suprematic.data.local.database.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suprematic.domain.entities.Game

@Entity
class DbGame(
    @PrimaryKey
    val id: Long,
    val timeStamp: Long = 0,
    val teamOne: DbTeam?,
    val teamTwo: DbTeam?,
    val sport: DbSport?,
    val pointsTeamOne: Int = 0,
    val pointsTeamTwo: Int = 0,
    val duration: Long = 0,
    val isInProgress: Boolean = false,
    val isPaused: Boolean = false
)

fun DbGame.toDomainModel(): Game =
    Game(
        id = id,
        timeStamp = timeStamp,
        teamOne = teamOne!!.toDomainModel(),
        teamTwo = teamTwo!!.toDomainModel(),
        sport = sport!!.toDomainModel(),
        pointsTeamOne = pointsTeamOne,
        pointsTeamTwo = pointsTeamTwo,
        duration = duration,
        isInProgress = isInProgress,
        isPaused = isPaused
    )

fun Game.toDbEntity(): DbGame =
    DbGame(
        id = id,
        timeStamp = timeStamp,
        teamOne = teamOne!!.toDbEntity(),
        teamTwo = teamTwo!!.toDbEntity(),
        sport = sport.toDbEntity(),
        pointsTeamOne = pointsTeamOne,
        pointsTeamTwo = pointsTeamTwo,
        duration = duration,
        isInProgress = isInProgress,
        isPaused = isPaused
    )