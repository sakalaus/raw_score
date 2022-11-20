package com.suprematic.feature.games.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.suprematic.feature.games.GamesRoute


const val gamesNavigationRoute = "games_route"

fun NavController.navigateToGames(navOptions: NavOptions? = null) {
    this.navigate(gamesNavigationRoute, navOptions)
}

fun NavGraphBuilder.gamesScreen() {
    composable(route = gamesNavigationRoute) {
        GamesRoute()
    }
}
