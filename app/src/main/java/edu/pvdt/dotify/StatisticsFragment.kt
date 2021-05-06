package edu.pvdt.dotify

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.pvdt.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()
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
            val song = safeArgs.song
//            val timesPlayed = safeArgs.timesPlayed
            val songCount = dotifyApp.songCount

            ivStatsAlbumCover.setImageResource(song.largeImageID)
            tvStatsContent.text = getString(R.string.statistics_times_played, song.title, songCount)
        }
        return binding.root
    }
}