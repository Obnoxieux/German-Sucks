package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import de.davidbattefeld.germansucks.android.data.WordsRepository

class FavoritesViewModel(
    private val application: Application,
    wordsRepository: WordsRepository,
    savedStateHandle: SavedStateHandle,
):  GenericViewModel(application, wordsRepository, savedStateHandle) {
    val wordsList = wordsRepository.getAllWordsStream()
}