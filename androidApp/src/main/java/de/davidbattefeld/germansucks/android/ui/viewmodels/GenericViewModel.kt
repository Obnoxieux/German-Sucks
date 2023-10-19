package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import de.davidbattefeld.germansucks.android.classes.ShareLookupDataProvider
import de.davidbattefeld.germansucks.android.data.WordsRepository
import de.davidbattefeld.germansucks.android.model.Word
import de.davidbattefeld.germansucks.shared.classes.SharingMode
import de.davidbattefeld.germansucks.shared.classes.SharingService

abstract class GenericViewModel(
    private val application: Application,
    protected val wordsRepository: WordsRepository
) : AndroidViewModel(application) {
    var favoriteWords = mutableStateListOf<Word>()
    private val shareLookupDataProvider = ShareLookupDataProvider()

    fun copyWordToClipboard(currentWord: Word) {
        val clipboardManager = application.applicationContext.getSystemService(ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("", currentWord.sequence)
        clipboardManager.setPrimaryClip(clip)
    }

    fun lookupWordOnline(context: Context, service: SharingService,currentWord: String) {
        val urlIntent = Intent(
            Intent.ACTION_VIEW, Uri.parse(shareLookupDataProvider.getLookupURL(
            service = service,
            searchTerm = currentWord)
        ))
        context.startActivity(urlIntent)
    }

    fun shareWord(context: Context, mode: SharingMode, wordList: List<Word>) {
        val shareText = shareLookupDataProvider.getShareText(mode, wordList)

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    suspend fun saveWordToFavorites(word: Word) {
        word.isFavorite = true
        favoriteWords.add(word)
        wordsRepository.insertWord(word)
    }
}