package com.suprematic.domain.entities

class Game(
    val timeStamp: Long = 0,
    val teamOne: Team? = null,
    val teamTwo: Team? = null,
    val pointsTeamOne: Float = 0f,
    val pointsTeamTwo: Float = 0f,
    val duration: Long = 0,
    val gameInProgress: Boolean = false
)