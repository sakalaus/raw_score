package com.suprematic.domain.usecases

import com.suprematic.domain.usecases.settings.ObservePreferredSport

data class UseCases(
    val initializeGame: InitializeGame,
    val savePreferredSport: SavePreferredSport,
    val makeSureSportsAndTeamsExist: CheckAndCreateEssentialData,
    val observeSports: ObserveSports,
    val observeTeams: ObserveTeams,
    val observeGame: ObserveGame,
    val observeGames: ObserveGames,
    val observePreferredSport: ObservePreferredSport,
    val getGameInProgress: GetGameInProgress,
    val clearAllGamesAndTraces: ClearAllGamesAndTraces,
    val toggleGamePause: ToggleGamePause,
    val finalizeGame: FinalizeGame,
    val registerPointsScored: RegisterPointsScored,
    val undoLastGameTraceEntry: UndoLastGameTraceEntry,
    val updateGameDuration: UpdateGameDuration
)
