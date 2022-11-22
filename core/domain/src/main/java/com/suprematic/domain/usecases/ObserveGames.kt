package com.suprematic.domain.usecases

import com.suprematic.domain.Repository
import com.suprematic.domain.entities.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

const val dateFormatPattern = "dd.MM.yyyy hh:mm"

class ObserveGames @Inject constructor(
    private val repository: Repository
) {
    private val standardDateFormat = SimpleDateFormat(dateFormatPattern, Locale.getDefault())
    operator fun invoke(): Flow<Map<Game, String>> =
        repository.observeGames().map { games ->
            games.associateWith { game ->
                standardDateFormat.format(Calendar.getInstance().apply {
                    timeInMillis = game.timeStamp
                }
                    .time)
            }
        }
}
