package de.davidbattefeld.germansucks.android.classes

import de.davidbattefeld.germansucks.android.model.Word
import de.davidbattefeld.germansucks.shared.classes.SharingMode
import de.davidbattefeld.germansucks.shared.classes.SharingService

class ShareLookupDataProvider {
    fun getLookupURL(service: SharingService, searchTerm: String) : String {
        return service.url + searchTerm
    }

    fun getShareText(mode: SharingMode, wordList: List<Word>): String {
        val shareWordEntity = when (wordList.size == 1) {
            true -> wordList.first().sequence
            false -> wordList.joinToString(separator = "\n")
        }

        return when (mode) {
            SharingMode.SingleWord -> shareWordEntity
            SharingMode.WithSentence ->
                """
                I have to share this ridiculously long German word with you:
                Would you believe that "$shareWordEntity" is a real German word? I mean, what are they thinking? German really sucks!
                """.trimIndent()
            SharingMode.List ->
                """
                I have to share my favorite list of ridiculously long German words with you:
                $shareWordEntity
                Isn't that madness?
                """.trimIndent()
        }
    }
}