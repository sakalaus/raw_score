package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game

class GetGameInProgress(val repository: Repository) {
    suspend operator fun invoke(): Game? = repository.getGameInProgress()
}