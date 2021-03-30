package hera.com.clickergame.ui.score

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hera.com.clickergame.R

class ScoreListAdapter(private val viewModel: ScoreViewModel) :  RecyclerView.Adapter<ScoreListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scoreView: TextView = view.findViewById(R.id.score_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.score_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val score = viewModel.score.value!![position]
        val scoreTime = score.scoreTimeText
        val gameMode = score.gameMode
        val backgroundColor = when (gameMode) {
            "EASY" -> R.color.pink_100
            "MEDIUM" -> R.color.pink_600
            else -> R.color.pink_900
        }
        holder.scoreView.text = "Score: $scoreTime / Game Mode: $gameMode"
        holder.scoreView.setBackgroundResource(backgroundColor)
    }

    override fun getItemCount(): Int {
        return viewModel.score.value?.size ?: 0
    }
}