package de.davidbattefeld.germansucks.android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.davidbattefeld.germansucks.android.model.Stats
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(stats: Stats)

    @Update
    suspend fun update(stats: Stats)

    @Delete
    suspend fun delete(stats: Stats)

    @Query("SELECT * FROM stats WHERE uniqueID = :id")
    fun getStats(id: String): Flow<Stats>
}