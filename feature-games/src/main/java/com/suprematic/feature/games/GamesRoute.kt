package com.suprematic.feature.games

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Gamepad
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.suprematic.domain.entities.*
import com.suprematic.ui.composables.EmptyScreen
import com.suprematic.ui.compositionLocalProviders.ThemeExtras
import com.suprematic.ui.theme.RawScoreTheme
import com.suprematic.utils.formatAsTime
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun GamesRoute(
    viewModel: GamesViewModel = hiltViewModel()
) {
    val currentUiState = viewModel.uiState.collectAsState().value
    GamesScreen(games = currentUiState.games, noGamesFound = currentUiState.noGamesFound)
}

@Composable
fun GamesScreen(games: Map<Game, String>, noGamesFound: Boolean) {
    if (noGamesFound) {
        EmptyScreen(
            imageVector = Icons.Outlined.Gamepad,
            text = stringResource(id = R.string.no_saved_games_yet)
        )

    } else {
        GamesScreen(games = games)
    }
}

@Composable
fun GamesScreen(games: Map<Game, String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(games.keys.toList()) { game ->
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(96.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    GameItem(
                        game = game,
                        formattedDateTime = games.getOrDefault(game, "")
                    )
                }
            }
        }
    }
}


@Composable
fun GameItem(
    game: Game,
    formattedDateTime: String
) {
    GameDataRow(
        game = game,
        formattedDateTime = formattedDateTime)
    Divider(modifier = Modifier.fillMaxWidth())
    TeamPointsRow(team = game.teamOne, points = game.pointsTeamOne)
    TeamPointsRow(team = game.teamTwo, points = game.pointsTeamTwo)
}

@Composable
fun GameDataRow(
    game: Game,
    formattedDateTime: String) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(32.dp)
    ) {
        if (game.isInProgress) {
            val infiniteTransition = rememberInfiniteTransition()
            val colorInProgress by infiniteTransition.animateColor(
                initialValue = Color.Blue,
                targetValue = Color.Black,
                animationSpec = infiniteRepeatable(
                    animation = tween(3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(0.5f),
                text = game.sport.name,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = ThemeExtras.colors.captionColorSecondary
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(0.5f),
                text = "${stringResource(R.string.in_progress)} ${game.duration.formatAsTime()}",
                textAlign = TextAlign.End,
                fontSize = 14.sp,
                color = colorInProgress
            )
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(0.5f),
                text = game.sport.name,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = ThemeExtras.colors.captionColorSecondary
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .weight(0.5f),
                text = formattedDateTime,
                textAlign = TextAlign.End,
                fontSize = 14.sp,
                color = ThemeExtras.colors.captionColorSecondary
            )
        }
    }
}

@Composable
fun TeamPointsRow(team: Team?, points: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()

                .padding(horizontal = 8.dp, vertical = 4.dp)
                .weight(0.8f),
            text = team?.name ?: "",
            textAlign = TextAlign.Start,
            fontSize = 16.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .weight(0.8f),
            text = points.toString(),
            textAlign = TextAlign.End,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun GamesScreenPreview() {
    RawScoreTheme {
        GamesScreen(
            games = mapOf(
                Game(
                    id = 1,
                    timeStamp = System.currentTimeMillis(),
                    teamOne = teamOneIndiana,
                    teamTwo = teamTwoUtah,
                    pointsTeamOne = 124,
                    pointsTeamTwo = 111,
                    sport = basketball,
                    isInProgress = true
                ) to "12.11.2022 21:00:00",
                Game(
                    id = 1,
                    timeStamp = System.currentTimeMillis(),
                    teamOne = teamOneIndiana,
                    teamTwo = teamTwoUtah,
                    pointsTeamOne = 134,
                    pointsTeamTwo = 99,
                    sport = basketball
                ) to "11.11.2022 22:00:00",
            )
        )
    }
}