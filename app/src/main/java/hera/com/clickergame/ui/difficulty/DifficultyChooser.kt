package hera.com.clickergame.ui.difficulty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import hera.com.clickergame.R
import hera.com.clickergame.databinding.FragmentDifficultyChooserBinding

class DifficultyChooser : Fragment() {
    private var _binding: FragmentDifficultyChooserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Action Bar title.
        (activity as AppCompatActivity).supportActionBar?.title = "Difficulty"

        _binding = FragmentDifficultyChooserBinding.inflate(inflater, container, false)


        binding.easyMode.setOnClickListener {
            val bundle = bundleOf(DIFFICULTY to "easy")
            Navigation.findNavController(binding.root).navigate(R.id.action_difficultyChooser_to_game, bundle)
        }

        binding.mediumMode.setOnClickListener {
            val bundle = bundleOf(DIFFICULTY to "medium")
            Navigation.findNavController(binding.root).navigate(R.id.action_difficultyChooser_to_game, bundle)
        }

        binding.hardMode.setOnClickListener {
            val bundle = bundleOf(DIFFICULTY to "hard")
            Navigation.findNavController(binding.root).navigate(R.id.action_difficultyChooser_to_game, bundle)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val DIFFICULTY = "difficulty"
    }
}