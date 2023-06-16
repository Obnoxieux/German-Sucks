package de.davidbattefeld.germansucks.shared.classes

actual abstract class Context
object iosContext : Context()

actual class WordProviderPlatform actual constructor(context: Context) : WordProvider() {
    override fun getWords(): List<String> {
        val filename = "output.txt"
        //TODO: do iOS specific thing
        return listOf()
    }
}