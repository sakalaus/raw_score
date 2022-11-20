package com.suprematic.feature.score

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.PlayCircle
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.suprematic.domain.entities.*
import com.suprematic.ui.compositionLocalProviders.ThemeExtras
import com.suprematic.ui.icons.RsIcon
import com.suprematic.ui.icons.RsIcon.*

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
        ScoreBoard(game = game)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f),
            contentAlignment = Alignment.Center
        ) {
            if (isGameInitialized.not()) {
                GameNotStarted {
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
fun GameNotStarted(
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
fun ColumnScope.ScoreBoard(game: Game?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.25f),
        colors = CardDefaults.cardColors(
            containerColor = ThemeExtras.colors.scoreBoardBackgroundColor,
            contentColor = ThemeExtras.colors.captionColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    )
    {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(0.3f),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                color = ThemeExtras.colors.captionColor,
                fontSize = 28.sp,
                text = game?.teamOne?.name ?: ""
            )
            AnimatedPoints(points = game?.pointsTeamOne)
            Text(
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                color = ThemeExtras.colors.captionColor,
                fontSize = 48.sp,
                text = ":"
            )
            AnimatedPoints(points = game?.pointsTeamTwo)
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(0.3f),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                color = ThemeExtras.colors.captionColor,
                fontSize = 28.sp,
                text = game?.teamTwo?.name ?: ""
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RowScope.AnimatedPoints(points: Int?) {
    AnimatedContent(
        modifier = Modifier
            .wrapContentHeight()
            .weight(0.2f),
        targetState = points ?: 0,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { targetCount ->
        Text(
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal,
            color = ThemeExtras.colors.captionColor,
            fontSize = 42.sp,
            text = targetCount.toString()
        )
    }
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
fun NumericButton(
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
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
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
    pointsScored: Int,
    containerColor: Color,
    onPointsScored: (Team, Int) -> Unit
) {
    NumericButton(
        modifier = Modifier.size(124.dp),
        caption = pointsScored.toString(),
        captionSize = 48.sp,
        containerColor = containerColor,
        contentColor = ThemeExtras.colors.captionColor
    ) {
        onPointsScored(team, pointsScored)
    }
}

@Composable
fun RowScope.SinglePointButton(
    team: Team,
    pointsScored: Int,
    caption: String,
    containerColor: Color,
    onPointsScored: (Team, Int) -> Unit
) {
    NumericButton(
        modifier = Modifier
            .fillMaxHeight()
            .weight(0.5f)
            .padding(8.dp),
        caption = caption,
        captionSize = 48.sp,
        containerColor = containerColor,
        contentColor = ThemeExtras.colors.captionColor
    ) {
        onPointsScored(team, pointsScored)
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
            icon = if (game == null || game.isPaused) ImageVectorIcon(Icons.Filled.PlayCircle) else ImageVectorIcon(
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

//
//@Preview
//@Composable
//fun ScoreBoardPreview(
//){RawScoreTheme {
//    ScoreBoard(game = Game(
//        teamOne = Team(1, "Indiana Pacers"),
//        teamTwo = Team(2, "Milwaukee Bucks")
//    ))
//}}

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