package com.suprematic.feature.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Team
import com.suprematic.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
    private var currentObserver: Job? = null

    init {
        useCases.makeSureSportsAndTeamsExist()
        viewModelScope.launch {
            useCases.getGameInProgress()?.let { game ->
                _uiState.update { it.copy(isGameInitialized = true, game = game) }
                initializeObservers()
                launchTimer()
            }
        }
    }

    private fun initializeObservers() {
        currentObserver?.cancel()
        currentObserver = useCases.observeGame(game = currentUiState.game).onEach { updatedGame ->
            if (currentUiState.isGameInitialized) {
                _uiState.update { it.copy(game = updatedGame) }
            }
        }
            .launchIn(viewModelScope)
    }

    private fun launchTimer() {
        viewModelScope.launch(Dispatchers.Default) {
            while(true){
                if (currentUiState.isGameActive){
                    delay(1000L)
                    currentUiState.game?.let{ game ->
                        useCases.updateGameDuration(game = game)
                    }
                }
            }
        }
    }

    fun onEvent(event: ScoreUiEvent) {
        when (event) {
            is ScoreUiEvent.PointsScored -> registerPoints(
                game = currentUiState.game,
                team = event.team,
                pointsScored = event.points
            )
            ScoreUiEvent.GamePauseToggled -> toggleGamePause()
            ScoreUiEvent.GameInitialized -> initializeGame()
            ScoreUiEvent.GameFinalized -> finalizeGame()
            ScoreUiEvent.EntryUndone -> undoLatestGameTraceEntry()
        }
    }

    private fun registerPoints(game: Game?, team: Team?, pointsScored: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            game?.let { game ->
                team?.let { team ->
                    useCases.registerPointsScored(
                        game = game,
                        team = team,
                        pointsScored = pointsScored
                    )
                }
            }
        }
    }

    private fun undoLatestGameTraceEntry() {
        viewModelScope.launch(Dispatchers.IO) {
            currentUiState.game?.let { useCases.undoLastGameTraceEntry(it) }
        }
    }

    private fun toggleGamePause() {
        viewModelScope.launch(Dispatchers.IO) {
            currentUiState.game?.let { useCases.toggleGamePause(it) }
        }
    }

    private fun finalizeGame() {
        viewModelScope.launch {
            currentUiState.game?.let { useCases.finalizeGame(it) }
            _uiState.update { it.copy(isGameInitialized = false, game = null) }
            initializeObservers()
        }
    }

    private fun initializeGame() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isGameInitialized = true, game = useCases.initializeGame()) }
            initializeObservers()
            launchTimer()
        }
    }

}