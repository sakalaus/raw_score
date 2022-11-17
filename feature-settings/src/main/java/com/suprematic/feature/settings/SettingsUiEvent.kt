package com.suprematic.feature.settings

import com.suprematic.domain.entities.Sport

sealed class SettingsUiEvent() {
    class SportSelected(val sport: Sport): SettingsUiEvent()
}