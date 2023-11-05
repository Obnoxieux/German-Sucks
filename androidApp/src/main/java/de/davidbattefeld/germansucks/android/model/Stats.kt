package de.davidbattefeld.germansucks.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
data class Stats(
    @PrimaryKey
    var uniqueID: String,
    var totalWordsSeen: Int = 1,
    var totalCharactersSeen: Int = 0,
    var timesClickedLookUp: Int = 0,
    var totalWordsShared: Int = 0,
    var longestWordLengthDiscovered: Int = 0,
) {
    var percentageOfWordsLookedUp = timesClickedLookUp.toDouble() / totalWordsSeen
}
