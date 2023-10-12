package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import de.davidbattefeld.germansucks.android.data.WordsRepository

class FavoritesViewModel(
    private val application: Application,
    wordsRepository: WordsRepository
):  GenericViewModel(application, wordsRepository) {

}