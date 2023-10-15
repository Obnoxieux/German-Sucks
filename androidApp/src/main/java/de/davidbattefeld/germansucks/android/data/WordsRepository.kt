package de.davidbattefeld.germansucks.android.data

import de.davidbattefeld.germansucks.android.model.Word
import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    fun getAllWordsStream(): Flow<List<Word>>

    fun getFavoriteWordsStream(): Flow<List<Word>>

    fun getWordStream(id: Int): Flow<Word?>

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)

    suspend fun updateWord(word: Word)
}