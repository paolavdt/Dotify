package edu.pvdt.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

            // handle listeners here...
        }
    }
}