package de.davidbattefeld.germansucks.android.data

import de.davidbattefeld.germansucks.android.model.Stats
import kotlinx.coroutines.flow.Flow

class OfflineStatsRepository(private val statsDao: StatsDao): StatsRepository {
    override fun getStatsStream(id: String): Flow<Stats?> = statsDao.getStats(id)

    override suspend fun insertStats(stats: Stats) = statsDao.insert(stats)

    override suspend fun deleteStats(stats: Stats) = statsDao.delete(stats)

    override suspend fun updateStats(stats: Stats) = statsDao.update(stats)
}