package edu.pvdt.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.Song
import edu.pvdt.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val navController by lazy { findNavController() }
    private val safeArgs: SettingsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val song: Song = safeArgs.song

        binding = FragmentSettingsBinding.inflate(layoutInflater)
        with(binding) {
            // add listeners
            btnProfile.setOnClickListener{navController.navigate(R.id.profileFragment)}
            btnAbout.setOnClickListener{navController.navigate(R.id.aboutFragment)}
            btnStatistics.setOnClickListener{
                navController.navigate(
                    SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment(
                        song = song
                    )
                )
            }
        }
        return binding.root
    }
}