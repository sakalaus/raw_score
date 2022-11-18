package com.suprematic.domain.usecases

import com.suprematic.domain.Repository

class ClearAllGamesAndTraces(val repository: Repository) {
    suspend operator fun invoke(){
        repository.clearAllGames()
        repository.clearAllGameTraces()
    }
}