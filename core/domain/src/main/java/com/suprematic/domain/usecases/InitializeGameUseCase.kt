package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import javax.inject.Inject

class InitializeGameUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(){
        repository.initializeGame()
    }
}