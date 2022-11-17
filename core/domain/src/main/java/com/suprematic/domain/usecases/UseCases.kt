package com.suprematic.domain.usecases

data class UseCases(
    val initializeGameUseCase: InitializeGameUseCase,
    val savePreferredSport: SavePreferredSport,
    val checkAndCreateEssentialData: CheckAndCreateEssentialData
)
