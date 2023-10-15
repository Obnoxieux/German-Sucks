package de.davidbattefeld.germansucks.android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.davidbattefeld.germansucks.android.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT * FROM words WHERE id = :id")
    fun getWord(id: Int): Flow<Word>

    @Query("SELECT * FROM words ORDER BY sequence ASC")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE isFavorite = 1 ORDER BY sequence ASC")
    fun getFavoriteWords(): Flow<List<Word>>
}