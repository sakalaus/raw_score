package com.suprematic.domain.usecases

import com.suprematic.domain.Repository

class ObserveGame(val repository: Repository) {
    operator fun invoke(gameId: Long) = repository.observeGame(gameId)
}