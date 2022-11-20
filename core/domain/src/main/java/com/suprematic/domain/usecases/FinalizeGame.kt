package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
import javax.inject.Inject

class FinalizeGame @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(game: Game) {
       repository.finalizeGame(game = game)
    }
}