package de.davidbattefeld.germansucks.classes

import android.content.Context

class WordProvider(private val context: Context) {
    private var completeWordList = listOf<String>()
    init {
        loadWordsFromFile()
    }
    private fun loadWordsFromFile() {
        val filename = "output.txt"
        val file = context.assets.open(filename).bufferedReader().readLines()
        completeWordList = file
    }

    fun getRandomWord(): String {
        if (completeWordList.isEmpty()) {
            loadWordsFromFile()
        }
        return completeWordList.random()
    }
}