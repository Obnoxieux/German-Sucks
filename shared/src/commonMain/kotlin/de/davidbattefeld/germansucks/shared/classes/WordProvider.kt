package de.davidbattefeld.germansucks.shared.classes

open class WordProvider {
    private var completeWordList = listOf<String>()
    init {
        loadWordsFromFile()
    }
    private fun loadWordsFromFile() {
        completeWordList = getWords()
    }
    open fun getWords(): List<String> {
        return listOf()
    }
    fun getRandomWord(): String {
        if (completeWordList.isEmpty()) {
            loadWordsFromFile()
        }
        return completeWordList.random()
    }
}