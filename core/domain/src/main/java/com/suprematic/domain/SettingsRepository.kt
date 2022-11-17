package com.suprematic.domain

import com.suprematic.domain.entities.Sport
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun savePreferredSport(key: String, value: Sport)
    fun observePreferredSport(key: String): Flow<Sport>
}