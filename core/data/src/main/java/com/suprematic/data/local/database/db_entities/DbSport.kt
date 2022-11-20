package com.suprematic.data.local.database.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suprematic.domain.entities.Sport

@Entity
@kotlinx.serialization.Serializable
data class DbSport(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun DbSport.toDomainModel(): Sport =
    Sport(
        id = id,
        name = name
    )

fun Sport.toDbEntity(): DbSport =
    DbSport(
        id = id,
        name = name
    )
