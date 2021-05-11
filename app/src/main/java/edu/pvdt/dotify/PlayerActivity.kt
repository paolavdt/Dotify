package edu.pvdt.dotify

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.lifecycleScope
import coil.load
import edu.pvdt.dotify.databinding.ActivityPlayerBinding
import edu.pvdt.dotify.repository.DataRepository
import kotlinx.coroutines.launch


fun navigateToPlayerActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

class MainActivity : AppCompatActivity() {
    lateinit var dotifyApp: DotifyApplication
    lateinit var dataRepository: DataRepository
    private var songCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // add application context
        dotifyApp = this.applicationContext as DotifyApplication
        songCount = dotifyApp.songCount

        // add repository
        dataRepository = dotifyApp.dataRepository

        // up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // add binding
        val binding = ActivityPlayerBinding.inflate(layoutInflater).apply{setContentView(root)}
        with(binding) {
            // update player activity info with current song info
            val song = dotifyApp.currSong
            if (song != null) {
                ivAlbumCover.load(song.largeImageURL)
                tvSongTitle.text = song.title
                tvArtistName.text = song.artist
            }

            // add listeners
            ibPrevTrack.setOnClickListener{skipPrevClicked()}
            ibNextTrack.setOnClickListener{skipNextClicked()}
            ibPlay.setOnClickListener{playClicked(tvPlayCount)}
//            if (song != null) {
//                btnSettings.setOnClickListener{ navigateToSettingsActivity(this@MainActivity, song)}
//            }
            btnSettings.setOnClickListener{ navigateToSettingsActivity(this@MainActivity)}

            // update play count
            tvPlayCount.text = getString(R.string.play_count, songCount)
        }
    }

    private fun skipPrevClicked() {
        if (dotifyApp.currSongPosition > 0) {
            lifecycleScope.launch{
                val songList = dataRepository.getSongs().songs
                dotifyApp.currSongPosition -= 1
                dotifyApp.currSong = songList[dotifyApp.currSongPosition]
                startActivity(getIntent());
                finish();
                overridePendingTransition(0, 0);
            }
        }
    }

    private fun skipNextClicked() {
        if (dotifyApp.currSongPosition < 47) {
            lifecycleScope.launch{
                val songList = dataRepository.getSongs().songs
                dotifyApp.currSongPosition += 1
                dotifyApp.currSong = songList[dotifyApp.currSongPosition]
                startActivity(getIntent());
                finish();
                overridePendingTransition(0, 0);
            }
        }
    }

    private fun playClicked(playCount: TextView) {
        dotifyApp.songCount += 1
        songCount = dotifyApp.songCount
        playCount.text = getString(R.string.play_count, songCount)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}