package de.davidbattefeld.germansucks.android.ui.viewmodels

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import de.davidbattefeld.germansucks.shared.classes.ShareLookupDataProvider
import de.davidbattefeld.germansucks.shared.classes.SharingService
import de.davidbattefeld.germansucks.shared.model.Word

abstract class GenericViewModel(private val application: Application) : AndroidViewModel(application) {
    var favoriteWords = mutableStateListOf<Word>()
    private val shareLookupDataProvider = ShareLookupDataProvider()

    fun copyWordToClipboard(currentWord: String) {
        val clipboardManager = application.applicationContext.getSystemService(ClipboardManager::class.java) as ClipboardManager
        val clip = ClipData.newPlainText("", currentWord)
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

    fun shareWord(context: Context, addText: Boolean, currentWord: String) {
        val shareText = shareLookupDataProvider.getShareText(currentWord)

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, if (addText) { shareText } else { currentWord })
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}