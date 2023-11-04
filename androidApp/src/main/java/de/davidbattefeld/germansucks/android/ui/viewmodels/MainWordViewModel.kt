package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import de.davidbattefeld.germansucks.android.data.WordsRepository
import de.davidbattefeld.germansucks.android.model.Word
import de.davidbattefeld.germansucks.shared.classes.WordProviderPlatform

class MainWordViewModel(
    application: Application,
    wordsRepository: WordsRepository,
    savedStateHandle: SavedStateHandle,
) : GenericViewModel(application, wordsRepository, savedStateHandle) {

    @OptIn(SavedStateHandleSaveableApi::class)
    var currentWord by savedStateHandle.saveable {
        mutableStateOf(Word(sequence =  "No word loaded"))
    }

    private val wordProvider = WordProviderPlatform(context = application.applicationContext)

    fun setCurrentWord() {
        currentWord = Word(sequence = wordProvider.getRandomWord())
    }
}