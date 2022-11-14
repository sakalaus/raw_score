package com.suprematic.feature.score.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.suprematic.feature.score.ScoreRoute

const val scoreNavigationRoute = "score_route"

fun NavController.navigateToScore(navOptions: NavOptions? = null) {
    this.navigate(scoreNavigationRoute, navOptions)
}

fun NavGraphBuilder.scoreScreen() {
    composable(route = scoreNavigationRoute) {
        ScoreRoute()
    }
}
