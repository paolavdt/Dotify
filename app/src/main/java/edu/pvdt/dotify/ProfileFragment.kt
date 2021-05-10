package edu.pvdt.dotify

import android.content.Context
import android.os.Bundle
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

class ProfileFragment : Fragment() {

    lateinit var dotifyApp: DotifyApplication
    private lateinit var dataRepository: DataRepository

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
            lifecycleScope.launch{
                val user = dataRepository.getUser()
                ivProfileImage.load(user.profilePicURL)
                tvProfileUsername.text = user.username
                tvFirstName.text = getString(R.string.profile_desc, "First Name", user.firstName)
                tvLastName.text = getString(R.string.profile_desc, "Last Name", user.lastName)
                tvHasNose.text = getString(R.string.profile_desc, "Has Nose", user.hasNose.toString())
            }
        }
        return binding.root
    }
}