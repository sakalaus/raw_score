package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.SettingsRepository
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.teamOneIndiana
import com.suprematic.domain.entities.teamTwoUtah
import com.suprematic.domain.usecases.settings.PREFERRED_SPORT_KEY
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.random.Random

class InitializeGame @Inject constructor(
    private val repository: Repository,
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke(): Game {
        val preferredSport = settingsRepository.getPreferredSport(PREFERRED_SPORT_KEY)
        val game = Game(
            id = Random.nextLong(),
            timeStamp = System.currentTimeMillis(),
            teamOne = teamOneIndiana,
            teamTwo = teamTwoUtah,
            sport = preferredSport,
            isInProgress = true
        )
        repository.createGames(listOf(game))
        return game
    }
}