package com.suprematic.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.suprematic.data.converters.JsonConverter
import com.suprematic.data.local.database.db_entities.DbSport
import com.suprematic.data.local.database.db_entities.toDbEntity
import com.suprematic.data.local.database.db_entities.toDomainModel
import com.suprematic.domain.SettingsRepository
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.entities.basketball
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonConverter: JsonConverter
) : SettingsRepository {
    override suspend fun savePreferredSport(key: String, value: Sport) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = jsonConverter.toJson(value.toDbEntity())
        }
    }

    override fun observePreferredSport(key: String): Flow<Sport> {
        val dataStoreKey = stringPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[dataStoreKey]?.let { str ->
                jsonConverter.fromJson<DbSport>(str)
            }?.toDomainModel() ?: basketball
        }
    }

    override suspend fun getPreferredSport(key: String): Sport {
        val dataStoreKey = stringPreferencesKey(key)
        return context.dataStore.data.first().toPreferences()[dataStoreKey]?.let { str ->
            jsonConverter.fromJson<DbSport>(str)
        }?.toDomainModel() ?: basketball
    }
}
