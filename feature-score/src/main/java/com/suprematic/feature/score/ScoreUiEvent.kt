package com.suprematic.feature.score

import com.suprematic.domain.entities.Team

sealed class ScoreUiEvent(
    val team: Team? = null,
    val points: Int = 0
) {
    object GameInitialized : ScoreUiEvent()
    object GamePaused: ScoreUiEvent()
    object GameFinalized: ScoreUiEvent()
    object EntryUndone: ScoreUiEvent()
    class PointsScored(team: Team?, points: Int): ScoreUiEvent(team, points)
}