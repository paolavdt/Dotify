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
    private var songCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // add application context
        val dotifyApp = this.applicationContext as DotifyApplication
        songCount = dotifyApp.songCount


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
                btnSettings.setOnClickListener{ navigateToSettingsActivity(this@MainActivity, song, songCount)}
            }

            // update play count
            tvPlayCount.text = getString(R.string.play_count, songCount)
        }
    }

    private fun skipPrevClicked() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun skipNextClicked() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    private fun playClicked(playCount: TextView) {
        songCount += 1
        playCount.text = getString(R.string.play_count, songCount)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}