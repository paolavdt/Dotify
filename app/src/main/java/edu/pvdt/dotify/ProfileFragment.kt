package edu.pvdt.dotify

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import coil.load
import edu.pvdt.dotify.databinding.FragmentProfileBinding
import edu.pvdt.dotify.databinding.FragmentStatisticsBinding
import edu.pvdt.dotify.repository.DataRepository
import kotlinx.coroutines.launch
import java.lang.Error
import java.lang.Exception

class ProfileFragment : Fragment() {

    lateinit var dotifyApp: DotifyApplication
    lateinit var dataRepository: DataRepository

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
        dataRepository = dotifyApp.dataRepository
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(layoutInflater)
        with(binding) {
            lifecycleScope.launch {
                try {
                    val user = dataRepository.getUser()
                    ivProfileImage.load(user.profilePicURL)
                    tvProfileUsername.text = user.username
                    tvName.text = getString(R.string.profile_name, user.firstName, user.lastName)
                    tvNose.text = user.hasNose.toString()
                    tvPlatform.text = user.platform.toString()
                    tvError.visibility = View.GONE
                } catch (exception: Exception) {
                    tvError.visibility = View.VISIBLE
                    Toast.makeText(activity, exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
        return binding.root
    }
}