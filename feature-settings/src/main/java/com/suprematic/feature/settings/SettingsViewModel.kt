package com.suprematic.feature.settings

import androidx.lifecycle.ViewModel
import com.suprematic.domain.entities.Sport
import com.suprematic.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(useCases: UseCases): ViewModel() {
    private val _uiState = MutableStateFlow(SettingsState())
    val uiState = _uiState.asStateFlow()

    init{

    }

    fun onEvent(event:SettingsUiEvent){
        when (event){
            is SettingsUiEvent.SportSelected -> onSportSelected(event.sport)
        }
    }

    private fun onSportSelected(sport: Sport){

    }

}