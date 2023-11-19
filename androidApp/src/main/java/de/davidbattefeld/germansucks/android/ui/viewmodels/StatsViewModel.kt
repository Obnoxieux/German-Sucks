package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import de.davidbattefeld.germansucks.android.data.StatsRepository
import de.davidbattefeld.germansucks.android.data.WordsRepository

class StatsViewModel(
    private val application: Application,
    wordsRepository: WordsRepository,
    statsRepository: StatsRepository,
    ): GenericViewModel(application, wordsRepository, statsRepository) {
}