package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.davidbattefeld.germansucks.android.classes.ShareLookupDataProvider
import de.davidbattefeld.germansucks.android.data.StatsRepository
import de.davidbattefeld.germansucks.android.data.WordsRepository
import de.davidbattefeld.germansucks.android.model.Stats
import de.davidbattefeld.germansucks.android.model.Word
import de.davidbattefeld.germansucks.shared.classes.SharingMode
import de.davidbattefeld.germansucks.shared.classes.SharingService
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

abstract class GenericViewModel(
    private val application: Application,
    protected val wordsRepository: WordsRepository,
    protected val statsRepository: StatsRepository,
) : AndroidViewModel(application) {
    private val shareLookupDataProvider = ShareLookupDataProvider()
    var stats = statsRepository.getStatsStream(Stats.DEFAULT_ID)

    fun copyWordToClipboard(currentWord: Word) {
        val clipboardManager = application.applicationContext.getSystemService(ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("", currentWord.sequence)
        clipboardManager.setPrimaryClip(clip)
    }

    fun lookupWordOnline(context: Context, service: SharingService, currentWord: Word) {
        val urlIntent = Intent(
            Intent.ACTION_VIEW, Uri.parse(shareLookupDataProvider.getLookupURL(
            service = service,
            searchTerm = currentWord.sequence)
        ))
        context.startActivity(urlIntent)
        viewModelScope.launch {
            stats.firstOrNull()?.let {
                it.timesClickedLookUp += 1
                statsRepository.updateStats(it)
            }
        }
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
        viewModelScope.launch {
            stats.firstOrNull()?.let {
                it.totalWordsShared += 1
                statsRepository.updateStats(it)
            }
        }
    }

    suspend fun saveWordToFavorites(word: Word) {
        word.isFavorite = true
        wordsRepository.insertWord(word)
    }

    suspend fun deleteWordFromFavorites(word: Word) {
        word.isFavorite = false
        wordsRepository.deleteWord(word)
    }
}