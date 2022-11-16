package com.suprematic.feature.score

import androidx.lifecycle.ViewModel
import com.suprematic.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel()  {

    private val _uiState = MutableStateFlow(ScoreUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ScoreUiEvent){
        when (event){
            is ScoreUiEvent.PointsScored -> TODO()
            ScoreUiEvent.GamePaused -> TODO()
            ScoreUiEvent.GameInitialized -> TODO()
            ScoreUiEvent.GameFinalized -> TODO()
        }
    }

    private fun initializeGame(){
        useCases.initializeGameUseCase()
    }

}