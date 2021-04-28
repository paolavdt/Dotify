package edu.pvdt.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.*
import com.ericchee.songdataprovider.Song
import edu.pvdt.dotify.databinding.ActivityPlayerBinding
import kotlin.random.Random

private const val COUNT_VALUE_KEY = "count_value"
private const val SONG_KEY = "curr_song"

fun navigateToPlayerActivity(context: Context, song: Song) {
    val intent = Intent(context, MainActivity::class.java)
    val bundle = Bundle().apply{
        putParcelable(SONG_KEY, song)
    }
    intent.putExtras(bundle)

    context.startActivity(intent)
}

class MainActivity : AppCompatActivity() {
    private var randomNumber = Random.nextInt(1000, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        // check if there is a saved instance state
        if (savedInstanceState != null) {
            randomNumber = savedInstanceState.getInt(COUNT_VALUE_KEY, randomNumber)
            Log.i("MainActivity", randomNumber.toString())
        }

        // up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // get current song object
        val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)

        // add binding
        val binding = ActivityPlayerBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            // update player activity info with current song info
            val song = song
            if (song != null) {
                ivAlbumCover.setImageResource(song.largeImageID)
                tvSongTitle.text = song.title
                tvArtistName.text = song.artist
            }

            // add listeners
            ibPrevTrack.setOnClickListener{skipPrevClicked()}
            ibNextTrack.setOnClickListener{skipNextClicked()}
            ibPlay.setOnClickListener{playClicked(tvPlayCount)}
            if (song != null) {
                btnSettings.setOnClickListener{ navigateToSettingsActivity(this@MainActivity, song, randomNumber)}
            }

            // update play count
            tvPlayCount.text = getString(R.string.play_count, randomNumber)
        }
    }

    private fun skipPrevClicked() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun skipNextClicked() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    private fun playClicked(playCount: TextView) {
        randomNumber += 1
        playCount.text = getString(R.string.play_count, randomNumber)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNT_VALUE_KEY, randomNumber)
        super.onSaveInstanceState(outState)
    }
}