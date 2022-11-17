package com.suprematic.data.local.database.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suprematic.domain.entities.Team

@Entity
@kotlinx.serialization.Serializable
data class DbTeam(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun DbTeam.toDomainModel(): Team  =
    Team(
        id = id,
        name = name
    )

fun Team.toDbEntity(): DbTeam  =
    DbTeam(
        id = id,
        name = name
    )