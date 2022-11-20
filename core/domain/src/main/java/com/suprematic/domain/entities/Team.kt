package com.suprematic.domain.entities

data class Team(
    val id: Long,
    val name: String
)

val teamOneIndiana = Team(1, "Indiana Pacers")
val teamTwoUtah = Team(2, "Utah Jazz")