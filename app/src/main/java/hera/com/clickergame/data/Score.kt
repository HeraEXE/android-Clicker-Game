package hera.com.clickergame.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class Score(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "score_time")
    val scoreTime: Long,

    @ColumnInfo(name = "score_time_text")
    val scoreTimeText: String,

    @ColumnInfo(name = "game_mode")
    val gameMode: String

)