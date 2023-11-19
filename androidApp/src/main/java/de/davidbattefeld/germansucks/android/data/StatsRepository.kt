package de.davidbattefeld.germansucks.android.data

import de.davidbattefeld.germansucks.android.model.Stats
import kotlinx.coroutines.flow.Flow

interface StatsRepository {
    fun getStatsStream(id: String): Flow<Stats?>

    suspend fun insertStats(stats: Stats)

    suspend fun deleteStats(stats: Stats)

    suspend fun updateStats(stats: Stats)
}