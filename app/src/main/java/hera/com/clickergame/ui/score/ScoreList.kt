package hera.com.clickergame.ui.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hera.com.clickergame.R
import hera.com.clickergame.data.ScoreDatabase
import hera.com.clickergame.databinding.FragmentScoreListBinding

class ScoreList : Fragment() {
    private var _binding: FragmentScoreListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: ScoreViewModelFactory
    private lateinit var viewModel: ScoreViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Action Bar title.
        (activity as AppCompatActivity).supportActionBar?.title = "Score List"

        _binding = FragmentScoreListBinding.inflate(inflater, container, false)

        val db = ScoreDatabase.getInstance(this.requireContext()).scoreDao()

        viewModelFactory = ScoreViewModelFactory(db)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        binding.recycler.adapter = ScoreListAdapter(viewModel)
        binding.recycler.layoutManager = LinearLayoutManager(this.requireContext())

        binding.deleteBadScoreButton.setOnClickListener {
            viewModel.clearScoreHistoryExceptBest()
            it.findNavController().navigate(R.id.action_global_scoreList)
        }

        viewModel.score.observe(viewLifecycleOwner, Observer {
            binding.recycler.adapter?.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}