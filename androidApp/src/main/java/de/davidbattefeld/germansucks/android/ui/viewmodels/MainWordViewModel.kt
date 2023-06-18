package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import de.davidbattefeld.germansucks.shared.classes.ShareLookupDataProvider
import de.davidbattefeld.germansucks.shared.classes.SharingService
import de.davidbattefeld.germansucks.shared.classes.WordProviderPlatform

class MainWordViewModel(private val application: Application) : GenericViewModel(application) {
    var currentWord = mutableStateOf("No word loaded")

    private val wordProvider = WordProviderPlatform(context = application.applicationContext)
    private val shareLookupDataProvider = ShareLookupDataProvider()

    fun setCurrentWord() {
        currentWord.value = wordProvider.getRandomWord()
    }

    fun copyWordToClipboard() {
        val clipboardManager = application.applicationContext.getSystemService(ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("", currentWord.value)
        clipboardManager.setPrimaryClip(clip)
    }

    fun lookupWordOnline(context: Context, service: SharingService) {
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(shareLookupDataProvider.getLookupURL(
            service = service,
            searchTerm = currentWord.value)
        ))
        context.startActivity(urlIntent)
    }

    fun shareWord(context: Context, addText: Boolean) {
        val shareText = shareLookupDataProvider.getShareText(currentWord.value)

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, if (addText) { shareText } else { currentWord.value })
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}