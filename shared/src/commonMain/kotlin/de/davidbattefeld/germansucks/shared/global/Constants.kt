package de.davidbattefeld.germansucks.shared.global

class Constants {
    private val explanationText = "This app loads a random, nightmarishly long word for your personal enjoyment. Data is taken from a comprehensive collection of German words longer than 25 characters."

    fun getExplanationText(): String {
        return explanationText
    }
}
