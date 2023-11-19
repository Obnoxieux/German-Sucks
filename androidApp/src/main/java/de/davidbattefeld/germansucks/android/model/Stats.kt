package de.davidbattefeld.germansucks.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.davidbattefeld.germansucks.shared.model.Stats

@Entity(tableName = "stats")
data class Stats(
    @PrimaryKey
    override var uniqueID: String,
    override var totalWordsSeen: Int = 1, //slightly off default value, but prevents division by zero
    override var totalCharactersSeen: Int = 0,
    override var timesClickedLookUp: Int = 0,
    override var totalWordsShared: Int = 0,
    override var longestWordLengthDiscovered: Int = 0,
): Stats {
    override var percentageOfWordsLookedUp = timesClickedLookUp.toDouble() / totalWordsSeen

    companion object {
        const val DEFAULT_ID = "gs-stats"
    }
}
