package com.suprematic.feature.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suprematic.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScoreUiState())
    val uiState = _uiState.asStateFlow()
    private val currentUiState
        get() = uiState.value

    init {
        useCases.makeSureSportsAndTeamsExist()
        viewModelScope.launch {
            useCases.clearAllGamesAndTraces()
            useCases.getGameInProgress()?.let { game ->
                _uiState.update { it.copy(game = game) }
            }
        }
        initializeObservers()
    }

    private fun initializeObservers() {
        currentUiState.game?.let { game ->
            useCases.observeGame(gameId = game.id).onEach { updatedGame ->
                _uiState.update { it.copy(game = updatedGame) }
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: ScoreUiEvent) {
        when (event) {
            is ScoreUiEvent.PointsScored -> TODO()
            ScoreUiEvent.GamePauseToggled -> toggleGamePause()
            ScoreUiEvent.GameInitialized -> initializeGame()
            ScoreUiEvent.GameFinalized -> TODO()
            ScoreUiEvent.EntryUndone -> TODO()
        }
    }

    private fun toggleGamePause() {
        viewModelScope.launch {
            useCases.toggleGamePause(game = currentUiState.game!!)
        }
    }

    private fun initializeGame() {
        viewModelScope.launch {
            _uiState.update { it.copy(game = useCases.initializeGame()) }
            initializeObservers()
        }
    }

}