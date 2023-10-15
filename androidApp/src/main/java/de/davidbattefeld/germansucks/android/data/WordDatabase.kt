package de.davidbattefeld.germansucks.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.davidbattefeld.germansucks.android.model.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var Instance: WordDatabase? = null

        fun getDatabase(context: Context): WordDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WordDatabase::class.java, "word_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}