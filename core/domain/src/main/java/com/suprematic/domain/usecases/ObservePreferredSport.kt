package com.suprematic.domain.usecases

import com.suprematic.domain.SettingsRepository
import com.suprematic.domain.entities.Sport
import javax.inject.Inject

class ObservePreferredSport @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(key: String){
        settingsRepository.observePreferredSport(
            key = key
        )
    }
}