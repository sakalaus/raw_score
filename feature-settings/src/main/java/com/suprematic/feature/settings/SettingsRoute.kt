package com.suprematic.feature.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.suprematic.domain.entities.Sport

@Composable
fun SettingsRoute(viewModel: SettingsViewModel = hiltViewModel()){
    val currentUiState = viewModel.uiState.collectAsState().value
    SettingsScreen(
        sports = currentUiState.sports,
        selectedSport = currentUiState.selectedSport,
        onSportSelect = {sport -> viewModel.onEvent(SettingsUiEvent.SportSelected(sport))}
    )
}
@Composable
fun SettingsScreen(
    sports: List<Sport>,
    selectedSport: Sport,
    onSportSelect: (Sport) -> Unit
){
    Column(modifier = Modifier.fillMaxSize()) {
        SportRadioButtonGroup(
            sports = sports,
            selectedSport = selectedSport,
            onSportSelect = onSportSelect
        )
    }
}

@Composable
fun SportRadioButtonGroup(
    sports: List<Sport>,
    selectedSport: Sport,
    onSportSelect: (Sport) -> Unit
) {

    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            sports.forEach { sport ->
                Row(Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (sport == selectedSport),
                        onClick = { onSportSelect(sport) }
                    )
                    .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (sport == selectedSport),
                        onClick = { onSportSelect(sport) }
                    )
                    Text(
                        text = sport.name,
                        style = MaterialTheme.typography.bodyMedium.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}