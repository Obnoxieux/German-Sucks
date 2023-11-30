package de.davidbattefeld.germansucks.shared.model

interface Stats {
    var uniqueID: String
    var totalWordsSeen: Int
    var totalCharactersSeen: Int
    var timesClickedLookUp: Int
    var totalWordsShared: Int
    var longestWordLengthDiscovered: Int
    var percentageOfWordsLookedUp: Double

    fun getPercentageLookedUp(): Number
}