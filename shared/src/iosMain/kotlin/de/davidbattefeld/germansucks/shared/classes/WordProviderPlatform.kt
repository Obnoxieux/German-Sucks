package de.davidbattefeld.germansucks.shared.classes

actual class WordProviderPlatform : WordProvider() {
    override fun getWords(): List<String> {
        val filename = "output.txt"
        //TODO: do iOS specific thing
        return listOf()
    }
}