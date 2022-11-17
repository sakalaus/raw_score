package com.suprematic.domain.entities

data class GameTrace(
    val timeStamp: Long,
    val game: Game,
    val pointsScoredTeamOne: Int,
    val pointsScoredTeamTwo: Int
)