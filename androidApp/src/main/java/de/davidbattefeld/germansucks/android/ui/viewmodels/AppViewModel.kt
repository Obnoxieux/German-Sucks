package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import de.davidbattefeld.germansucks.android.data.StatsRepository
import de.davidbattefeld.germansucks.android.data.WordsRepository
import de.davidbattefeld.germansucks.android.model.Stats
import kotlinx.coroutines.flow.firstOrNull

class AppViewModel(
    application: Application,
    wordsRepository: WordsRepository,
    statsRepository: StatsRepository,
) : GenericViewModel(application, wordsRepository, statsRepository) {
    /**
     * Stats is a Room model type, but there should be only one at a time that stores the
     * user's data. We therefore create one if it doesn't exist and do nothing in all
     * other cases.
     */
    suspend fun createStatsObjectIfNotExists() {
        val stats = statsRepository.getStatsStream(Stats.DEFAULT_ID).firstOrNull()

        if (stats == null) {
            val newStats = Stats(Stats.DEFAULT_ID)
            statsRepository.insertStats(newStats)
        }
    }
}