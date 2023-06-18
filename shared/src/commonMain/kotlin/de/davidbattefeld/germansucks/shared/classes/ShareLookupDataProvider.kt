package de.davidbattefeld.germansucks.shared.classes

class ShareLookupDataProvider {
    fun getLookupURL(service: SharingService, searchTerm: String) : String {
        return service.url + searchTerm
    }

    fun getShareText(word: String): String {
        return """
            I have to share this ridiculously long German word with you:
            Would you believe that "${word}" is a real German word? I mean, what are they thinking? German really sucks!
        """.trimIndent()
    }
}