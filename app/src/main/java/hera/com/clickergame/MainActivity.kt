package hera.com.clickergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import hera.com.clickergame.R
import hera.com.clickergame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.difficulty -> {
                Navigation.findNavController(binding.root).navigate(R.id.action_global_difficultyChooser)
                true
            }
            R.id.score -> {
                Navigation.findNavController(binding.root).navigate(R.id.action_global_scoreList)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}