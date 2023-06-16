package de.davidbattefeld.germansucks.shared.classes

actual typealias Context = android.content.Context

actual class WordProviderPlatform actual constructor(private val context: Context) : WordProvider() {
    override fun getWords(): List<String> {
        val filename = "output.txt"
        // this can in fact be null even though Intellisense claims otherwise
        return if (context == null) {
            emptyList()
        } else {
            context.assets.open(filename).bufferedReader().readLines()
        }
    }
}