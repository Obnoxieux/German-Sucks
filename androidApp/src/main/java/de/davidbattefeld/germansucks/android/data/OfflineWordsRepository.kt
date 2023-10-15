package de.davidbattefeld.germansucks.android.data

import de.davidbattefeld.germansucks.android.model.Word
import kotlinx.coroutines.flow.Flow

class OfflineWordsRepository(private val wordDao: WordDao): WordsRepository {
    override fun getAllWordsStream(): Flow<List<Word>> = wordDao.getAllWords()
    override fun getFavoriteWordsStream(): Flow<List<Word>> = wordDao.getFavoriteWords()

    override fun getWordStream(id: Int): Flow<Word?> = wordDao.getWord(id)

    override suspend fun insertWord(word: Word) = wordDao.insert(word)

    override suspend fun deleteWord(word: Word) = wordDao.delete(word)

    override suspend fun updateWord(word: Word) = wordDao.update(word)
}