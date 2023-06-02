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
    enum class Service {
        DEEPL, GOOGLE_TRANSLATE, DUDEN
    }

    //Totally not gonna work when they change their website, but well...
    private val DUDEN_URL = "https://www.duden.de/suchen/dudenonline/"
    private val DEEPL_URL = "https://www.deepl.com/translator#de/en/"
    private val GOOGLE_TRANSLATE_URL = "https://translate.google.com/?sl=de&tl=en&text="

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

    fun lookupWordOnline(context: Context, service: Service) {
        val url: String = when (service) {
            Service.DEEPL -> DEEPL_URL
            Service.GOOGLE_TRANSLATE -> GOOGLE_TRANSLATE_URL
            Service.DUDEN -> DUDEN_URL
        }
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url + currentWord.value))
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