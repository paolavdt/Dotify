package edu.pvdt.dotify

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import edu.pvdt.dotify.databinding.FragmentStatisticsBinding
import edu.pvdt.dotify.repository.DataRepository

class StatisticsFragment : Fragment() {

//    private val safeArgs: StatisticsFragmentArgs by navArgs()
    lateinit var dotifyApp: DotifyApplication

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStatisticsBinding.inflate(layoutInflater)
        with(binding) {
            val song = dotifyApp.currSong
            val songCount = dotifyApp.songCount
            val totalListeningTime = song.durationMillis * songCount / 1000

            ivStatsAlbumCover.load(song.largeImageURL)
            tvStatsContent.text = getString(R.string.statistics_times_played, song.title, songCount)
            tvSongDuration.text = getString(R.string.profile_name, song.durationMillis.toString(), "ms")
            tvTotalTime.text = getString(R.string.profile_name, totalListeningTime.toString(), "sec")
        }
        return binding.root
    }
}