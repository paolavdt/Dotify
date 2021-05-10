package edu.pvdt.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.pvdt.dotify.databinding.ActivitySongListBinding

private const val SELECTED_SONG_KEY = "selected_song"

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    private lateinit var selectedSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "All Songs"
        binding = ActivitySongListBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            val songs = SongDataProvider.getAllSongs()

            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter

            // Open mini player if selected song is not null (saved in instance state)
            if (savedInstanceState != null) {
                if (savedInstanceState.getParcelable<Song>(SELECTED_SONG_KEY) != null) {
                    var selectedSong = savedInstanceState.getParcelable<Song>(SELECTED_SONG_KEY)
                    if (selectedSong != null) {
                        updateMiniPlayer(selectedSong, tvMiniPlayerInfo, clMiniPlayer)
                    }
                }
            }

            // Handle when clicking on song from list (update mini player)
            adapter.onSongClickListener = {song ->
                selectedSong = song
                updateMiniPlayer(song, tvMiniPlayerInfo, clMiniPlayer)
            }

            // Handle shuffling songs
            btnShuffle.setOnClickListener{
                adapter.shuffleSongs(songs.toMutableList().shuffled())
            }

            // Handle when clicking on artists button
            btnArtists.setOnClickListener{navigateToArtistsActivity(this@SongListActivity)}
        }
    }

    private fun updateMiniPlayer(song: Song, mpInfo: TextView, mp: ConstraintLayout) {
        mpInfo.text = getString(R.string.mini_player, song.title, song.artist)
        mp.visibility = View.VISIBLE
        mp.setOnClickListener{navigateToPlayerActivity(this@SongListActivity, song)}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(SELECTED_SONG_KEY, selectedSong)
        super.onSaveInstanceState(outState)
    }
}