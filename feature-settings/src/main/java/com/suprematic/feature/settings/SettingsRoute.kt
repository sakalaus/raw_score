package com.suprematic.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.suprematic.domain.entities.*
import com.suprematic.ui.compositionLocalProviders.ThemeExtras
import com.suprematic.ui.theme.RawScoreTheme

@Composable
fun SettingsRoute(viewModel: SettingsViewModel = hiltViewModel()){
    val currentUiState = viewModel.uiState.collectAsState().value
    SettingsScreen(
        sports = currentUiState.sports,
        teams = currentUiState.teams,
        selectedSport = currentUiState.preferredSport,
        onSportSelect = {sport -> viewModel.onEvent(SettingsUiEvent.SportSelected(sport))}
    )
}
@Composable
fun SettingsScreen(
    sports: List<Sport>,
    teams: List<Team>,
    selectedSport: Sport,
    onSportSelect: (Sport) -> Unit
){
    Column(modifier = Modifier.fillMaxSize()) {
        SportRadioButtonGroup(
            sports = sports,
            selectedSport = selectedSport,
            onSportSelect = onSportSelect
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier= Modifier.fillMaxWidth().padding(horizontal = 8.dp))
        TeamColumn(teams = teams)
    }
}

@Composable
fun TeamColumn(teams: List<Team>){
    Text(
        modifier = Modifier.padding(8.dp),
        text = stringResource(R.string.teams)
    )
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(teams){ team ->
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        text = team.name,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                    )
                }
            }
        }
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
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier.background(ThemeExtras.colors.scoreBoardBackgroundColor)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                color = ThemeExtras.colors.captionColor,
                text = stringResource(R.string.sport)
            )
            Spacer(modifier = Modifier.height(16.dp))
            sports.forEach { sport ->
                Row(Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (sport == selectedSport),
                        onClick = { onSportSelect(sport) }
                    )
                    .padding(horizontal = 16.dp),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        modifier = Modifier.height(16.dp),
                        selected = (sport == selectedSport),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThemeExtras.colors.captionColor,
                            unselectedColor = ThemeExtras.colors.buttonCaptionColorDimmed

                        ),
                        onClick = { onSportSelect(sport) }
                    )
                    Text(
                        modifier = Modifier
                            .height(24.dp)
                            .padding(start = 16.dp),
                        text = sport.name,
                        fontSize = 16.sp,
                        color = if (sport == selectedSport) ThemeExtras.colors.captionColor else ThemeExtras.colors.buttonCaptionColorDimmed,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun SportRadioButtonPreview(){
    RawScoreTheme() {
        SportRadioButtonGroup(
            sports = listOf(basketball, football),
            selectedSport = basketball,
            onSportSelect = {})
    }
}

@Preview
@Composable
fun TeamColumnPreview(){
    RawScoreTheme() {
        TeamColumn(teams = listOf(teamOneIndiana, teamTwoUtah))
    }
}