package com.suprematic.domain.usecases

import com.suprematic.domain.SettingsRepository
import com.suprematic.domain.entities.Sport
import javax.inject.Inject

class SavePreferredSport @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(key: String, value: Sport){
        settingsRepository.savePreferredSport(
            key = key,
            value = value
        )
    }
}