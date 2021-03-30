package hera.com.clickergame.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class ScoreDatabase: RoomDatabase() {

    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile private var instance: ScoreDatabase? = null

        fun getInstance(context: Context): ScoreDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ScoreDatabase {
            return Room.databaseBuilder(context, ScoreDatabase::class.java, "score_database").build()
        }
    }
}