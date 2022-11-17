package com.suprematic.feature.score

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.Team
import com.suprematic.ui.compositionLocalProviders.ThemeExtras
import com.suprematic.ui.icons.RsIcon
import com.suprematic.ui.icons.RsIcon.*
import com.suprematic.ui.theme.RawScoreTheme

@Composable
fun ScoreRoute(
    viewModel: ScoreViewModel = hiltViewModel()
) {
    val currentUiState = viewModel.uiState.collectAsState().value
    ScoreScreen(
        game = currentUiState.game,
        isStarted = currentUiState.isStarted,
        isPaused = currentUiState.isPaused,
        onGameInitialized = { viewModel.onEvent(ScoreUiEvent.GameInitialized) },
        onGamePause = { viewModel.onEvent(ScoreUiEvent.GamePaused) },
        onGameFinalize = { viewModel.onEvent(ScoreUiEvent.GameFinalized) },
        onUndoLastEntry = { viewModel.onEvent(ScoreUiEvent.EntryUndone) },
        onPointsScored = { team: Team, points: Float ->
            viewModel.onEvent(
                ScoreUiEvent.PointsScored(
                    team = team,
                    points = points
                )
            )
        }
    )
}

@Composable
fun RoundedIconButton(
    modifier: Modifier,
    icon: RsIcon,
    tint: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        when (icon) {
            is ImageVectorIcon -> Icon(
                imageVector = icon.imageVector,
                contentDescription = "Start the match",
                modifier = Modifier.fillMaxSize(1.0f),
                tint = tint
            )
            is DrawableResourceIcon -> Icon(
                painter = painterResource(id = icon.id),
                contentDescription = "Start the match",
                modifier = Modifier.fillMaxSize(1.0f),
                tint = tint
            )
        }
    }
}

@Composable
fun SquareNumericButton(
    modifier: Modifier,
    caption: String,
    captionSize: TextUnit,
    containerColor: Color,
    contentColor: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        enabled = enabled,
        shape = androidx.compose.ui.graphics.RectangleShape
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = caption,
            fontSize = captionSize,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PointsButton(
    team: Team,
    pointsScored: Float,
    containerColor: Color,
    onPointsScored: (Team, Float) -> Unit
) {
    SquareNumericButton(
        modifier = Modifier.size(124.dp),
        caption = pointsScored.toInt().toString(),
        captionSize = 48.sp,
        containerColor = containerColor,
        contentColor = ThemeExtras.colors.buttonCaptionColor
    ) {
        onPointsScored(team, pointsScored)
    }
}

@Composable
fun ColumnScope.PointButtonRow(
    game: Game,
    pointsScored: Float,
    containerColor: Color,
    onPointsScored: (Team, Float) -> Unit
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
            team = game.teamOne!!,
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
fun ColumnScope.PointButtonGrid(
    game: Game,
    onPointsScored: (Team, Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.85f)
    ) {
        PointButtonRow(
            game = game,
            pointsScored = 3f,
            containerColor = ThemeExtras.colors.buttonOneColor,
            onPointsScored = onPointsScored
        )
        PointButtonRow(
            game = game,
            pointsScored = 2f,
            containerColor = ThemeExtras.colors.buttonTwoColor,
            onPointsScored = onPointsScored
        )
        PointButtonRow(
            game = game,
            pointsScored = 1f,
            containerColor = ThemeExtras.colors.buttonThreeColor,
            onPointsScored = onPointsScored
        )
    }
}

@Composable
fun ColumnScope.ControlButtonRow(
    onUndoLastEntry: () -> Unit,
    onGamePause: () -> Unit,
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
            icon = ImageVectorIcon(Icons.Filled.PlayCircle),
            tint = ThemeExtras.colors.smallIconColor
        ) {
            onGamePause()
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

@Composable
fun ScoreScreen(
    game: Game,
    isStarted: Boolean,
    isPaused: Boolean,
    onGameInitialized: () -> Unit,
    onGamePause: () -> Unit,
    onGameFinalize: () -> Unit,
    onUndoLastEntry: () -> Unit,
    onPointsScored: (Team, Float) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25f)
                .background(color = ThemeExtras.colors.scoreBoardBackgroundColor)
        ){
            ScoreBoard(
                game = game
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f),
            contentAlignment = Alignment.Center
        ) {
            if (isStarted.not()) {
                GameNotStarted {
                    onGameInitialized()
                }
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.85f)
                            .systemBarsPadding()
                    ) {
                        ControlButtonRow(
                            onUndoLastEntry = onUndoLastEntry,
                            onGamePause = onGamePause,
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
fun ScoreBoard(game: Game) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .weight(0.3f),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal,
            color = ThemeExtras.colors.buttonCaptionColor,
            fontSize = 28.sp,
            text = game.teamOne?.name ?: ""
        )
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .weight(0.2f),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal,
            color = ThemeExtras.colors.buttonCaptionColor,
            fontSize = 48.sp,
            text = game.pointsTeamOne.toInt().toString()
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal,
            color = ThemeExtras.colors.buttonCaptionColor,
            fontSize = 48.sp,
            text = ":"
        )
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .weight(0.2f),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal,
            color = ThemeExtras.colors.buttonCaptionColor,
            fontSize = 48.sp,
            text = game.pointsTeamTwo.toInt().toString()
        )
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .weight(0.3f),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal,
            color = ThemeExtras.colors.buttonCaptionColor,
            fontSize = 28.sp,
            text = game.teamTwo?.name ?: ""
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun GameNotStarted(
    onGameInitialized: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = ThemeExtras.colors.mainCanvasColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RoundedIconButton(
            modifier = Modifier.size(128.dp),
            icon = ImageVectorIcon(Icons.Filled.PlayArrow),
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

@Preview
@Composable
fun ScoreBoardPreview(
){RawScoreTheme {
    ScoreBoard(game = Game(
        teamOne = Team(1, "Indiana Pacers"),
        teamTwo = Team(2, "Milwaukee Bucks")
    ))
}}

//@Preview
//@Composable
//fun SquareNumericButtonPreview() {
//    RawScoreTheme {
//        SquareNumericButton(
//            modifier = Modifier.size(96.dp),
//            caption = "1",
//            captionSize = 24.sp,
//            containerColor = Color.Red,
//            contentColor = Color.White
//        ) {
//        }
//    }
//}

//@Preview
//@Composable
//fun RoundedIconButtonPreview() {
//    RawScoreTheme {
//        RoundedIconButton(
//            modifier = Modifier.size(64.dp),
//            icon = ImageVectorIcon(Icons.Filled.Replay),
//            tint = ThemeExtras.colors.bigIconColor,
//        ) {
//        }
//    }
//}