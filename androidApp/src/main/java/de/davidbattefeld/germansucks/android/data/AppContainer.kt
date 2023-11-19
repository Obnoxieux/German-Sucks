package de.davidbattefeld.germansucks.android.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val wordsRepository: WordsRepository
    val statsRepository: StatsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineWordsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [WordsRepository]
     */
    override val wordsRepository: WordsRepository by lazy {
        OfflineWordsRepository(WordDatabase.getDatabase(context).wordDao())
    }

    override val statsRepository: StatsRepository by lazy {
        OfflineStatsRepository(WordDatabase.getDatabase(context).statsDao())
    }
}