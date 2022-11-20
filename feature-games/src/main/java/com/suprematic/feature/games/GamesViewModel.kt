package com.suprematic.feature.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suprematic.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(val useCases: UseCases): ViewModel() {
    private val _uiState = MutableStateFlow(GamesUiState())
    val uiState = _uiState.asStateFlow()
    val currentUiState: GamesUiState
    get() = uiState.value

    init{
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        useCases.observeGames().onEach { games ->
            _uiState.update { it.copy(games = games) }
        }.launchIn(viewModelScope)
    }
}