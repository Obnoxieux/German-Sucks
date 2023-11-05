package de.davidbattefeld.germansucks.android.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.davidbattefeld.germansucks.android.model.Stats
import de.davidbattefeld.germansucks.android.model.Word

@Database(
    entities = [Word::class, Stats::class],
    version = 2,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun statsDao(): StatsDao

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