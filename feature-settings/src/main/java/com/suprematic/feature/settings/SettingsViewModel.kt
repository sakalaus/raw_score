package com.suprematic.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val useCases: UseCases): ViewModel() {
    private val _uiState = MutableStateFlow(SettingsState())
    val uiState = _uiState.asStateFlow()

    init{
        initializeObservers()
    }

    private fun initializeObservers() {
        useCases.observeSports().onEach { sports ->
            _uiState.update { it.copy(sports = sports) }
        }.launchIn(viewModelScope)

        useCases.observeTeams().onEach { teams ->
            _uiState.update { it.copy(teams = teams) }
        }.launchIn(viewModelScope)

        useCases.observePreferredSport().onEach { preferredSport ->
            _uiState.update { it.copy( preferredSport = preferredSport) }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event:SettingsUiEvent){
        when (event){
            is SettingsUiEvent.SportSelected -> onSportSelected(event.sport)
        }
    }

    private fun onSportSelected(sport: Sport){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.savePreferredSport(sport)
        }
    }

}