package com.suprematic.domain.usecases

import com.suprematic.domain.usecases.settings.ObservePreferredSport

data class UseCases(
    val initializeGame: InitializeGame,
    val savePreferredSport: SavePreferredSport,
    val makeSureSportsAndTeamsExist: CheckAndCreateEssentialData,
    val observeSports: ObserveSports,
    val observeTeams: ObserveTeams,
    val observeGame: ObserveGame,
    val observePreferredSport: ObservePreferredSport,
    val getGameInProgress: GetGameInProgress,
    val clearAllGamesAndTraces: ClearAllGamesAndTraces,
    val toggleGamePause: ToggleGamePause
)
