package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.entities.Team
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveGames @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Game>> = repository.observeGames()
}