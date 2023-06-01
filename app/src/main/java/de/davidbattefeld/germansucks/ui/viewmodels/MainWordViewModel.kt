package de.davidbattefeld.germansucks.ui.viewmodels

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import de.davidbattefeld.germansucks.classes.WordProvider

class MainWordViewModel(private val application: Application) : GenericViewModel(application) {
    //Totally not gonna work when they change their website, but well...
    private val DUDEN_WEBSITE_URL = "https://www.duden.de/suchen/dudenonline/"
    private val DEEPL_URL = "https://www.deepl.com/translator#de/en/"

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

    fun lookupWordOnline(context: Context) {
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(DEEPL_URL + currentWord.value))
        context.startActivity(urlIntent)
    }

    fun shareWord(context: Context, addText: Boolean) {
        val shareText = """
            I have to share this ridiculously long German word with you:
            Would you believe that "${currentWord.value}" is a real German word? I mean, what are they thinking? German really sucks!
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, if (addText) { shareText } else { currentWord.value })
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}