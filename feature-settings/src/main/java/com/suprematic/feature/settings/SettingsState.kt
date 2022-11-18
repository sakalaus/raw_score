package com.suprematic.feature.settings

import com.suprematic.domain.entities.Sport
import com.suprematic.domain.entities.Team
import com.suprematic.domain.entities.basketball

data class SettingsState(
    val sports: List<Sport> = emptyList(),
    val preferredSport: Sport = basketball,
    val teams: List<Team> = emptyList()
)