package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import androidx.lifecycle.viewModelScope
import de.davidbattefeld.germansucks.android.data.WordsRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val application: Application,
    wordsRepository: WordsRepository
):  GenericViewModel(application, wordsRepository) {
    init {
        viewModelScope.launch {
            wordsRepository.getFavoriteWordsStream().collect {
                it.forEach { word ->
                    favoriteWords.add(word)
                }
            }
        }
    }
}