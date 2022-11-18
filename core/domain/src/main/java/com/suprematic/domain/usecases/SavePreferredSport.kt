package com.suprematic.domain.usecases

import com.suprematic.domain.SettingsRepository
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.usecases.settings.PREFERRED_SPORT_KEY
import javax.inject.Inject

class SavePreferredSport @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(value: Sport){
        settingsRepository.savePreferredSport(
            key = PREFERRED_SPORT_KEY,
            value = value
        )
    }
}