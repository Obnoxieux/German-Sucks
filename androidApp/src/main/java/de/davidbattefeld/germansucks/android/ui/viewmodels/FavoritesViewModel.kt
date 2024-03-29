package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import de.davidbattefeld.germansucks.android.data.StatsRepository
import de.davidbattefeld.germansucks.android.data.WordsRepository

class FavoritesViewModel(
    private val application: Application,
    statsRepository: StatsRepository,
    wordsRepository: WordsRepository,
):  GenericViewModel(application, wordsRepository, statsRepository) {
    val wordsList = wordsRepository.getAllWordsStream()
}