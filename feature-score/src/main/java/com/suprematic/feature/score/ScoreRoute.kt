package com.suprematic.feature.score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Team

@Composable
fun ScoreRoute(
    viewModel: ScoreViewModel = hiltViewModel()
) {
    val currentUiState = viewModel.uiState.collectAsState().value
    ScoreScreen(
        game = currentUiState.game,
        onGameInitialized = { viewModel.onEvent(ScoreUiEvent.GameInitialized) },
        onGamePaused = { viewModel.onEvent(ScoreUiEvent.GamePaused) },
        onGameFinalized = { viewModel.onEvent(ScoreUiEvent.GameFinalized) },
        onPointsScored = { team: Team, points: Float ->
            viewModel.onEvent(
                ScoreUiEvent.PointsScored(
                    team = team,
                    points =points
                )
            )
        }
    )
}

@Composable
fun ScoreScreen(
    game: Game,
    onGameInitialized: () -> Unit,
    onGamePaused: () -> Unit,
    onGameFinalized: () -> Unit,
    onPointsScored: (Team, Float) -> Unit,
) {
}