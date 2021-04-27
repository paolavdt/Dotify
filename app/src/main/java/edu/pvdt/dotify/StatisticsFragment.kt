package edu.pvdt.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.pvdt.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStatisticsBinding.inflate(layoutInflater)
        with(binding) {
            val song = safeArgs.song
            val timesPlayed = safeArgs.timesPlayed

            ivStatsAlbumCover.setImageResource(song.largeImageID)
            tvStatsContent.text = getString(R.string.statistics_times_played, song.title, timesPlayed)
        }
        return binding.root
    }
}