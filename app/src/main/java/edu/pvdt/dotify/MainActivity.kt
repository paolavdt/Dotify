package edu.pvdt.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.pvdt.dotify.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var randomNumber = Random.nextInt(1000, 10000)
    private lateinit var playCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            // add listeners
            ibPrevTrack.setOnClickListener{skipPrevClicked()}
            ibNextTrack.setOnClickListener{skipNextClicked()}
            ibPlay.setOnClickListener{playClicked(tvPlayCount)}
            btnChangeUser.setOnClickListener{changeUserName(btnChangeUser, tvUsername, etChangeUserName)}

            // update play count
            tvPlayCount.text = getString(R.string.play_count, randomNumber)
        }
    }

    private fun skipPrevClicked() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun skipNextClicked() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    private fun playClicked(playCount: TextView) {
        randomNumber += 1
        playCount.text = getString(R.string.play_count, randomNumber)
    }

    private fun changeUserName(btnChangeUser: Button, tvUsername: TextView, etUsername: EditText) {
//        val tvUsername = findViewById<TextView>(R.id.tvUsername)
//        val etUsername = findViewById<EditText>(R.id.etChangeUserName)

        if (btnChangeUser.text.toString() == getString(R.string.change_user)) {
            // hide username text view
            tvUsername.visibility = View.GONE

            // show username edit text
            etUsername.visibility = View.VISIBLE

            // change text of 'change username' button to 'apply'
            btnChangeUser.text = getString(R.string.apply)
        } else if (btnChangeUser.text == getString(R.string.apply) && etUsername.text.toString().trim() != ""){
            // show username text view with updated username
            tvUsername.text = etUsername.text.toString()
            tvUsername.visibility = View.VISIBLE

            // hide username edit text & set to empty text value
            etUsername.text.clear()
            etUsername.visibility = View.GONE

            // change text of 'apply' button to 'change username'
            btnChangeUser.text = getString(R.string.change_user)
        }
    }
}