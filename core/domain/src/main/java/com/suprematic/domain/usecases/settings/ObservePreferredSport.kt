package com.suprematic.domain.usecases.settings

import com.suprematic.domain.SettingsRepository
import com.suprematic.domain.entities.Sport
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val PREFERRED_SPORT_KEY = "PREFERRED_SPORT"
class ObservePreferredSport @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<Sport> =
        settingsRepository.observePreferredSport(
            key = PREFERRED_SPORT_KEY
        )
}