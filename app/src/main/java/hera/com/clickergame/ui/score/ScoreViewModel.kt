package hera.com.clickergame.ui.score

import androidx.lifecycle.ViewModel
import hera.com.clickergame.data.Score
import hera.com.clickergame.data.ScoreDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScoreViewModel(private val db: ScoreDao) : ViewModel() {

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _score = db.getAllScore()
    val score get() = _score

    private val scoreOfBest: MutableList<Score?> = mutableListOf()

    fun addScore(score: Score) {
        uiScope.launch {
            db.addScore(score)
        }
    }

    fun clearScoreHistoryExceptBest() {
        uiScope.launch {
            scoreOfBest.add(db.getBestEasyModeScore())
            scoreOfBest.add(db.getBestMediumModeScore())
            scoreOfBest.add(db.getBestHardModeScore())
            db.deleteAllScore()
            for (i in scoreOfBest.indices)
                if (scoreOfBest[i] != null) addScore(scoreOfBest[i] as Score)
        }
    }


}