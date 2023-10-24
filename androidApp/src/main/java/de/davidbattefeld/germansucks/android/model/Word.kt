package de.davidbattefeld.germansucks.android.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "words")
@Parcelize
data class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var sequence: String
) : Parcelable {
    companion object {
        const val WORD_LENGTH_SHORT = 30
        const val WORD_LENGTH_MEDIUM = 34
    }

    var length: Int = sequence.length
    var isFavorite = false
    var firstCharacter = sequence.first().toString().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }

    override fun toString(): String {
        return "$sequence ($length characters)"
    }
}
