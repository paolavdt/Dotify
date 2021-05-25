package edu.pvdt.dotify

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.pvdt.dotify.databinding.FragmentSettingsBinding
import edu.pvdt.dotify.manager.FetchSongsManager

class SettingsFragment : Fragment() {

    lateinit var dotifyApp: DotifyApplication
    lateinit var fetchSongsManager: FetchSongsManager
    private lateinit var binding: FragmentSettingsBinding
    private val navController by lazy { findNavController() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
        fetchSongsManager = dotifyApp.fetchSongsManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        with(binding) {
            // add listeners
            btnProfile.setOnClickListener{navController.navigate(R.id.profileFragment)}
            btnAbout.setOnClickListener{navController.navigate(R.id.aboutFragment)}
            btnStatistics.setOnClickListener{ navController.navigate(R.id.statisticsFragment)}
            scRefresh.setOnCheckedChangeListener{_, isChecked, -> if(isChecked){fetchSongsManager.fetchSongs()}}
        }
        return binding.root
    }
}