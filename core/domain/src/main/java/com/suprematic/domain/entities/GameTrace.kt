package com.suprematic.domain.entities

data class GameTrace(
    val timeStamp: Long,
    val gameId: Long,
    val team: Team,
    val pointsScored: Int
)