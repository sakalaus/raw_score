package com.suprematic.feature.score.widgets

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suprematic.domain.entities.Game
import com.suprematic.domain.entities.mockGame
import com.suprematic.feature.score.ScoreScreen
import com.suprematic.ui.compositionLocalProviders.ThemeExtras
import com.suprematic.ui.theme.RawScoreTheme
import com.suprematic.utils.formatAsTime

@Composable
fun ColumnScope.ScoreBoard(
    game: Game?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.20f),
        colors = CardDefaults.cardColors(
            containerColor = ThemeExtras.colors.scoreBoardBackgroundColor,
            contentColor = ThemeExtras.colors.captionColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    )
    {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(if (game == null) 1f else 0.8f),
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
        game?.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal,
                    color = ThemeExtras.colors.captionColor,
                    fontSize = 20.sp,
                    text =
                    "${game.duration.formatAsTime()} ${if (game.isPaused) " (paused)" else ""}"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
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


@Preview
@Composable
fun ScoreScreenPreview(
) {
    RawScoreTheme() {
        ScoreScreen(
            game = mockGame,
            isGameInitialized = true,
            onGameInitialized = {},
            onGamePause = {},
            onGameFinalize = {},
            onUndoLastEntry = {},
            onPointsScored = { _, _ -> }
        )
    }
}