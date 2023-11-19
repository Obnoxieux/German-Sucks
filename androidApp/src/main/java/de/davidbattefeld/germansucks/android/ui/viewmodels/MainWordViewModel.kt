package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import de.davidbattefeld.germansucks.android.data.StatsRepository
import de.davidbattefeld.germansucks.android.data.WordsRepository
import de.davidbattefeld.germansucks.android.model.Word
import de.davidbattefeld.germansucks.shared.classes.WordProviderPlatform
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainWordViewModel(
    application: Application,
    wordsRepository: WordsRepository,
    statsRepository: StatsRepository,
    savedStateHandle: SavedStateHandle,
) : GenericViewModel(application, wordsRepository, statsRepository) {

    @OptIn(SavedStateHandleSaveableApi::class)
    var currentWord by savedStateHandle.saveable {
        mutableStateOf(Word(sequence =  "No word loaded"))
    }

    private val wordProvider = WordProviderPlatform(context = application.applicationContext)

    fun setCurrentWord() {
        currentWord = Word(sequence = wordProvider.getRandomWord())
        viewModelScope.launch {
            stats.firstOrNull()?.let {
                it.totalWordsSeen += 1
                it.totalCharactersSeen += currentWord.length

                if (currentWord.length > it.longestWordLengthDiscovered) {
                    it.longestWordLengthDiscovered = currentWord.length
                }
                statsRepository.updateStats(it)
            }
        }
    }
}