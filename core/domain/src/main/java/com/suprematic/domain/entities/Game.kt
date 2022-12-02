package com.suprematic.domain.entities

class Game(
    val id: Long,
    val timeStamp: Long = 0,
    val teamOne: Team? = null,
    val teamTwo: Team? = null,
    val sport: Sport,
    val pointsTeamOne: Int = 0,
    val pointsTeamTwo: Int = 0,
    val duration: Long = 0,
    val isInProgress: Boolean = false,
    val isPaused: Boolean = false
)


val mockGame = Game(
    id = -1L,
    teamOne = teamOneIndiana,
    teamTwo = teamTwoUtah,
    sport = basketball,
    pointsTeamOne = 54,
    pointsTeamTwo = 48,
    isInProgress = true
)
