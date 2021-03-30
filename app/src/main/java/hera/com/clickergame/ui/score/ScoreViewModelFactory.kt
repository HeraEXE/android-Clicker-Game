package hera.com.clickergame.ui.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hera.com.clickergame.MainActivity
import hera.com.clickergame.data.ScoreDao
import java.lang.IllegalArgumentException


class ScoreViewModelFactory(private val db: ScoreDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java))
            return ScoreViewModel(db) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}