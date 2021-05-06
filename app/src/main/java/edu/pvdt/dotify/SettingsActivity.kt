package edu.pvdt.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.ericchee.songdataprovider.Song
import edu.pvdt.dotify.databinding.ActivitySettingsBinding

private const val SONG_KEY = "song"
private const val PLAYED_KEY = "timesPlayed"

fun navigateToSettingsActivity(context: Context, song: Song) {
    val intent = Intent(context, SettingsActivity::class.java)
    val bundle = Bundle().apply{
        putParcelable(SONG_KEY, song)
    }
    intent.putExtras(bundle)
    context.startActivity(intent)
}

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val navController by lazy {findNavController(R.id.navHost)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater).apply{setContentView(root)}
        with(binding) {
            navController.setGraph(R.navigation.nav_graph, intent.extras)
        }
    }
}