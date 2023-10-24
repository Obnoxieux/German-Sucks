package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import de.davidbattefeld.germansucks.android.data.WordsRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val application: Application,
    wordsRepository: WordsRepository,
    savedStateHandle: SavedStateHandle,
):  GenericViewModel(application, wordsRepository, savedStateHandle) {
    init {
        if (favoriteWords.isEmpty()) {
            viewModelScope.launch {
                wordsRepository.getFavoriteWordsStream().collect {
                    it.forEach { word ->
                        favoriteWords.add(word)
                    }
                }
            }
        }
    }
}