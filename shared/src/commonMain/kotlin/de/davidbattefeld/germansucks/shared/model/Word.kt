package de.davidbattefeld.germansucks.shared.model

data class Word(
    val sequence: String
) {
    companion object {
        const val WORD_LENGTH_SHORT = 30
        const val WORD_LENGTH_MEDIUM = 34
    }

    val length: Int = sequence.length
    val firstCharacter = sequence.first().toString().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}
