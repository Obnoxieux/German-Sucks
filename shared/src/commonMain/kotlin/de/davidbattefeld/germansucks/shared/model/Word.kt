package de.davidbattefeld.germansucks.shared.model

data class Word(
    val sequence: String
) {
    val length: Int = sequence.length
    val firstCharacter = sequence.first().toString().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}
