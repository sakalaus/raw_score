package com.suprematic.feature.score

import androidx.lifecycle.ViewModel
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Team
import com.suprematic.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScoreUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                game = Game(
                    teamOne = Team(1, "Indiana Pacers"),
                    teamTwo = Team(2, "Utah Jazz")
                )
            )
        }
    }

    fun onEvent(event: ScoreUiEvent) {
        when (event) {
            is ScoreUiEvent.PointsScored -> TODO()
            ScoreUiEvent.GamePaused -> TODO()
            ScoreUiEvent.GameInitialized -> initializeGame()
            ScoreUiEvent.GameFinalized -> TODO()
            ScoreUiEvent.EntryUndone -> TODO()
        }
    }

    private fun initializeGame() {
        useCases.initializeGameUseCase()
    }

}