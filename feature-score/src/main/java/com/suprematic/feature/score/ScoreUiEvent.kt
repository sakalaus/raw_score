package com.suprematic.feature.score

import com.suprematic.domain.entities.Team

sealed class ScoreUiEvent(
    val team: Team? = null,
    val points: Float = 0f
) {
    object GameInitialized : ScoreUiEvent()
    object GamePaused: ScoreUiEvent()
    object GameFinalized: ScoreUiEvent()
    class PointsScored(team: Team?, points: Float): ScoreUiEvent(team, points)
}