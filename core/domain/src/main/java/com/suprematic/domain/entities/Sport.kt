package com.suprematic.domain.entities

data class Sport(
    val id: Long,
    val name: String
)

val basketball = Sport(1, "Basketball")
val football = Sport(2, "Football")

