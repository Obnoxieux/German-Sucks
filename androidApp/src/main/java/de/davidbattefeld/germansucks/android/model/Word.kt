package de.davidbattefeld.germansucks.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sequence: String
) {
    companion object {
        const val WORD_LENGTH_SHORT = 30
        const val WORD_LENGTH_MEDIUM = 34
    }

    val length: Int = sequence.length
    val isFavorite = false
    val firstCharacter = sequence.first().toString().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}
