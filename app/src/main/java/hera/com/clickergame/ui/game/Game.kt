package hera.com.clickergame.ui.game

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import hera.com.clickergame.R
import hera.com.clickergame.data.Score
import hera.com.clickergame.data.ScoreDatabase
import hera.com.clickergame.databinding.FragmentGameBinding
import hera.com.clickergame.ui.score.ScoreViewModel
import hera.com.clickergame.ui.score.ScoreViewModelFactory

class Game : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    private var clicks = 0
    private var difficulty = ""
    private val levels: Map<String, Int> = mapOf(
        "easy" to 500,
        "medium" to 1000,
        "hard" to 2000
    )
    private val clickCounterText get() = "$clicks/${levels[difficulty]}"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Action Bar title.
        (activity as AppCompatActivity).supportActionBar?.title = "Score List"

        // Binding
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        // Database Dao init.
        val db = ScoreDatabase.getInstance(this.requireContext()).scoreDao()

        // View Model
        viewModelFactory = ScoreViewModelFactory(db)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        // Get Saved Data in Bundle.
        if (savedInstanceState != null) {
            binding.chronometer.base = savedInstanceState.getLong(TIME_VALUE)
            clicks = savedInstanceState.getInt(CLICKS)
        }

        // Get Difficulty.
        difficulty = arguments?.getString(DIFFICULTY) as String
        // Action Bar title.
        (activity as AppCompatActivity).supportActionBar?.title = difficulty.toUpperCase()
        binding.clickCounter.text = clickCounterText
        binding.chronometer.start()

        binding.clickButton.setOnClickListener {
            clicks++
            binding.clickCounter.text = clickCounterText
            // After game finished it adds new score.
            if (clicks >= levels[difficulty] as Int) {
                val time = SystemClock.elapsedRealtime() - binding.chronometer.base
                val timeText = binding.chronometer.text.toString()
                val gameMode = difficulty.toUpperCase()
                viewModel.addScore(Score(0, time, timeText, gameMode))
                it.findNavController().navigate(R.id.action_global_scoreList)
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(TIME_VALUE, binding.chronometer.base)
        outState.putInt(CLICKS, clicks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val DIFFICULTY = "difficulty"
        const val TIME_VALUE = "time_value"
        const val CLICKS = "clicks"
    }
}