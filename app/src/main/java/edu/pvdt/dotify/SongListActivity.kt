package edu.pvdt.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.SongDataProvider
import edu.pvdt.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"
        binding = ActivitySongListBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            val songs = SongDataProvider.getAllSongs()

            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter

            // Handle when clicking on song from list (update mini player)
            adapter.onSongClickListener = {song ->
                tvMiniPlayerInfo.text = getString(R.string.mini_player, song.title, song.artist)
                clMiniPlayer.visibility = View.VISIBLE
                clMiniPlayer.setOnClickListener{navigateToPlayerActivity(this@SongListActivity, song)}
            }

            // Handle shuffling songs
            btnShuffle.setOnClickListener{
                adapter.shuffleSongs(songs.toMutableList().shuffled())
            }
        }
    }
}