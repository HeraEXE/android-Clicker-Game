package hera.com.clickergame.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {

    @Insert
    suspend fun addScore(score: Score)

    @Query("SELECT * FROM score_table ORDER BY score_time ASC")
    fun getAllScore(): LiveData<List<Score>>

    @Query("DELETE FROM score_table")
    suspend fun deleteAllScore()

    @Query("SELECT * FROM score_table WHERE game_mode = 'EASY' ORDER BY score_time ASC LIMIT 1")
    suspend fun getBestEasyModeScore(): Score?

    @Query("SELECT * FROM score_table WHERE game_mode = 'MEDIUM' ORDER BY score_time ASC LIMIT 1")
    suspend fun getBestMediumModeScore(): Score?

    @Query("SELECT * FROM score_table WHERE game_mode = 'HARD' ORDER BY score_time ASC LIMIT 1")
    suspend fun getBestHardModeScore(): Score?

}