package com.suprematic.rawscore.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.material.icons.filled.VideoStable
import androidx.compose.material.icons.outlined.Scoreboard
import androidx.compose.material.icons.outlined.SettingsSuggest
import androidx.compose.material.icons.outlined.VideoStable
import androidx.compose.ui.graphics.vector.ImageVector
import com.suprematic.feature.games.R as gamesRes
import com.suprematic.feature.score.R as scoreRes
import com.suprematic.feature.settings.R as settingsRes

enum class RsTopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int
) {
    SCORE(
        selectedIcon = Icons.Filled.Scoreboard,
        unselectedIcon = Icons.Outlined.Scoreboard,
        iconTextId = scoreRes.string.score,
        titleTextId = scoreRes.string.score
    ),
    GAMES(
        selectedIcon = Icons.Filled.VideoStable,
        unselectedIcon = Icons.Outlined.VideoStable,
        iconTextId = gamesRes.string.games,
        titleTextId = gamesRes.string.games,
    ),
    SETTINGS(
        selectedIcon = Icons.Filled.SettingsSuggest,
        unselectedIcon = Icons.Outlined.SettingsSuggest,
        iconTextId = settingsRes.string.settings,
        titleTextId = settingsRes.string.settings,
    )
}