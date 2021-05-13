package edu.pvdt.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import coil.load
import edu.pvdt.dotify.databinding.ActivitySongListBinding
import edu.pvdt.dotify.model.Song
import edu.pvdt.dotify.model.Songs
import edu.pvdt.dotify.repository.DataRepository
import kotlinx.coroutines.launch
import java.lang.Exception

private const val SELECTED_SONG_KEY = "selected_song"

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    private lateinit var selectedSong: Song

    private val dotifyApp: DotifyApplication by lazy { application as DotifyApplication}
    private val dataRepository: DataRepository by lazy { dotifyApp.dataRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "All Songs"
        binding = ActivitySongListBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            lifecycleScope.launch{
                try {
                    val songListObject: Songs = dataRepository.getSongs()
                    val songList = songListObject.songs

                    val adapter = SongListAdapter(songList)
                    rvSongs.adapter = adapter

                    // Handle when clicking on song from list (update mini player)
                    adapter.onSongClickListener = {song ->
                        selectedSong = song
                        dotifyApp.currSongPosition = songList.indexOf(selectedSong)
                        updateMiniPlayer(song, tvMiniPlayerInfo, clMiniPlayer)
                    }

                    // Handle shuffling songs
                    btnShuffle.setOnClickListener{
                        adapter.shuffleSongs(songList.toMutableList().shuffled())
                    }

                    // make exception invisible
                    tvErrorSongList.visibility = View.GONE
                } catch (exception: Exception) {
                    tvErrorSongList.visibility = View.VISIBLE
                    Toast.makeText(this@SongListActivity, exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateMiniPlayer(song: Song, mpInfo: TextView, mp: ConstraintLayout) {
        mpInfo.text = getString(R.string.mini_player, song.title, song.artist)
        mp.visibility = View.VISIBLE
        dotifyApp.currSong = song
        mp.setOnClickListener{navigateToPlayerActivity(this@SongListActivity)}
    }
}