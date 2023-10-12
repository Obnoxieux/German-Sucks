package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import de.davidbattefeld.germansucks.android.data.WordsRepository
import de.davidbattefeld.germansucks.shared.classes.WordProviderPlatform

class MainWordViewModel(
    application: Application,
    wordsRepository: WordsRepository
) : GenericViewModel(application, wordsRepository) {
    var currentWord = mutableStateOf("No word loaded")

    private val wordProvider = WordProviderPlatform(context = application.applicationContext)

    fun setCurrentWord() {
        currentWord.value = wordProvider.getRandomWord()
    }
}