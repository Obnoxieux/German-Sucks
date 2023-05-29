package de.davidbattefeld.germansucks.ui.viewmodels

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.runtime.mutableStateOf
import de.davidbattefeld.germansucks.classes.WordProvider

class MainWordViewModel(private val application: Application) : GenericViewModel(application) {
    var currentWord = mutableStateOf("No word loaded")
    private val wordProvider = WordProvider(context = application.applicationContext)

    fun setCurrentWord() {
        currentWord.value = wordProvider.getRandomWord()
    }

    fun copyWordToClipboard() {
        val clipboardManager = application.applicationContext.getSystemService(ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("", currentWord.value)
        clipboardManager.setPrimaryClip(clip)
    }
}