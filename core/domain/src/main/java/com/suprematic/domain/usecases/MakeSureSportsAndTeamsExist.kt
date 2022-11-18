package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.basketball
import com.suprematic.domain.entities.football
import com.suprematic.domain.entities.teamOneIndiana
import com.suprematic.domain.entities.teamTwoUtah
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckAndCreateEssentialData @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(){
        val job = CoroutineScope(context = Dispatchers.IO)
        val teams = job.launch {
            val teams =
                withContext(Dispatchers.IO) { repository.getTeams() }
            if (teams.isEmpty()){
                repository.createTeams(listOf(teamOneIndiana, teamTwoUtah))
            }
            val sports =
                withContext(Dispatchers.IO) { repository.getSports() }
            if (teams.isEmpty()){
                repository.createSports(listOf(basketball, football))
            }
        }
    }
}