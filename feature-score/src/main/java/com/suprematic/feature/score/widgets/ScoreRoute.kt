package com.suprematic.feature.score

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.suprematic.domain.entities.*
import com.suprematic.feature.score.widgets.PointsButton
import com.suprematic.feature.score.widgets.RoundedIconButton
import com.suprematic.feature.score.widgets.ScoreBoard
import com.suprematic.feature.score.widgets.SinglePointButton
import com.suprematic.ui.compositionLocalProviders.ThemeExtras
import com.suprematic.ui.icons.RsIcon.ImageVectorIcon

@Composable
fun ScoreRoute(
    viewModel: ScoreViewModel = hiltViewModel()
) {
    val currentUiState = viewModel.uiState.collectAsState().value
    ScoreScreen(
        game = currentUiState.game,
        isGameInitialized = currentUiState.isGameInitialized,
        onGameInitialized = { viewModel.onEvent(ScoreUiEvent.GameInitialized) },
        onGamePause = { viewModel.onEvent(ScoreUiEvent.GamePauseToggled) },
        onGameFinalize = { viewModel.onEvent(ScoreUiEvent.GameFinalized) },
        onUndoLastEntry = { viewModel.onEvent(ScoreUiEvent.EntryUndone) }
    ) { team: Team, points: Int ->
        viewModel.onEvent(
            ScoreUiEvent.PointsScored(
                team = team,
                points = points
            )
        )
    }
}

@Composable
fun ScoreScreen(
    game: Game?,
    isGameInitialized: Boolean,
    onGameInitialized: () -> Unit,
    onGamePause: () -> Unit,
    onGameFinalize: () -> Unit,
    onUndoLastEntry: () -> Unit,
    onPointsScored: (Team, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        ScoreBoard(
            game = game
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f),
            contentAlignment = Alignment.Center
        ) {
            if (isGameInitialized.not()) {
                GameNotStartedScreen {
                    onGameInitialized()
                }
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = isGameInitialized,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
                ),

            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.85f)
                            .systemBarsPadding()
                    ) {
                        ControlButtonRow(
                            game = game,
                            onUndoLastEntry = onUndoLastEntry,
                            onGamePauseToggle = onGamePause,
                            onGameFinalize = onGameFinalize
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        PointButtonGrid(
                            game = game,
                            onPointsScored = onPointsScored
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun GameNotStartedScreen(
    onGameInitialized: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RoundedIconButton(
            modifier = Modifier.size(128.dp),
            icon = ImageVectorIcon(Icons.Outlined.PlayCircle),
            tint = ThemeExtras.colors.scoreBoardBackgroundColor
        ) {
            onGameInitialized()
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .wrapContentSize(),
            style = TextStyle(
                color = ThemeExtras.colors.bigIconColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            text = stringResource(id = R.string.start_match)
        )
    }
}

@Composable
fun ColumnScope.PointButtonGrid(
    game: Game?,
    onPointsScored: (Team, Int) -> Unit
) {
    if (game?.sport == basketball) {
        ThreePointButtonGrid(
            game = game,
            onPointsScored = onPointsScored
        )
    } else {
        OnePointButtonGrid(
            game = game,
            onPointsScored = onPointsScored
        )
    }
}

@Composable
fun ColumnScope.ThreePointButtonGrid(
    game: Game?,
    onPointsScored: (Team, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.85f)
    ) {
        PointButtonRow(
            game = game,
            pointsScored = 3,
            containerColor = ThemeExtras.colors.buttonOneColor,
            onPointsScored = onPointsScored
        )
        PointButtonRow(
            game = game,
            pointsScored = 2,
            containerColor = ThemeExtras.colors.buttonTwoColor,
            onPointsScored = onPointsScored
        )
        PointButtonRow(
            game = game,
            pointsScored = 1,
            containerColor = ThemeExtras.colors.buttonThreeColor,
            onPointsScored = onPointsScored
        )
    }
}

@Composable
fun ColumnScope.OnePointButtonGrid(
    game: Game?,
    onPointsScored: (Team, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.85f)
    ) {
        SinglePointButtonRow(
            game = game,
            pointsScored = 1,
            containerColorOne = ThemeExtras.colors.buttonOneColor,
            containerColorTwo = ThemeExtras.colors.buttonTwoColor,
            onPointsScored = onPointsScored
        )
    }
}

@Composable
fun ColumnScope.PointButtonRow(
    game: Game?,
    pointsScored: Int,
    containerColor: Color,
    onPointsScored: (Team, Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PointsButton(
            team = game?.teamOne!!,
            pointsScored = pointsScored,
            containerColor = containerColor,
            onPointsScored = { team, pointsScored -> onPointsScored(team, pointsScored) }
        )
        PointsButton(
            team = game.teamTwo!!,
            pointsScored = pointsScored,
            containerColor = containerColor,
            onPointsScored = { team, pointsScored -> onPointsScored(team, pointsScored) }
        )
    }
}

@Composable
fun ColumnScope.SinglePointButtonRow(
    game: Game?,
    pointsScored: Int,
    containerColorOne: Color,
    containerColorTwo: Color,
    onPointsScored: (Team, Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
            .weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SinglePointButton(
            team = game?.teamOne ?: teamOneIndiana,
            pointsScored = pointsScored,
            caption = "1",
            containerColor = containerColorOne,
            onPointsScored = { team, pointsScored -> onPointsScored(team, pointsScored) }
        )
        SinglePointButton(
            team = game?.teamTwo ?: teamTwoUtah,
            pointsScored = pointsScored,
            caption = "2",
            containerColor = containerColorTwo,
            onPointsScored = { team, pointsScored -> onPointsScored(team, pointsScored) }
        )
    }
}

@Composable
fun ColumnScope.ControlButtonRow(
    game: Game?,
    onUndoLastEntry: () -> Unit,
    onGamePauseToggle: () -> Unit,
    onGameFinalize: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.15f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RoundedIconButton(
            modifier = Modifier.size(64.dp),
            icon = ImageVectorIcon(Icons.Filled.Replay),
            tint = ThemeExtras.colors.smallIconColor
        ) {
            onUndoLastEntry()
        }
        Spacer(modifier = Modifier.width(24.dp))
        RoundedIconButton(
            modifier = Modifier.size(64.dp),
            icon = if (game?.isPaused == true) ImageVectorIcon(Icons.Filled.PlayCircle) else ImageVectorIcon(
                Icons.Filled.PauseCircle
            ),
            tint = ThemeExtras.colors.smallIconColor
        ) {
            onGamePauseToggle()
        }
        Spacer(modifier = Modifier.width(24.dp))
        RoundedIconButton(
            modifier = Modifier.size(64.dp),
            icon = ImageVectorIcon(Icons.Filled.StopCircle),
            tint = ThemeExtras.colors.smallIconColor
        ) {
            onGameFinalize()
        }
    }
}


