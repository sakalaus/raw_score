package com.suprematic.rawscore.di

import com.suprematic.domain.Repository
import com.suprematic.domain.SettingsRepository
import com.suprematic.domain.usecases.*
import com.suprematic.domain.usecases.settings.ObservePreferredSport
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository,
        settingsRepository: SettingsRepository
    ): UseCases =
        UseCases(
            initializeGame = InitializeGame(repository,settingsRepository),
            savePreferredSport = SavePreferredSport(settingsRepository),
            makeSureSportsAndTeamsExist = CheckAndCreateEssentialData(repository),
            observeSports = ObserveSports(repository),
            observeTeams = ObserveTeams(repository),
            observePreferredSport = ObservePreferredSport(settingsRepository),
            getGameInProgress = GetGameInProgress(repository),
            clearAllGamesAndTraces = ClearAllGamesAndTraces(repository),
            toggleGamePause = ToggleGamePause(repository),
            observeGame = ObserveGame(repository)
        )
}