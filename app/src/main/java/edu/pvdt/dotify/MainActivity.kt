package edu.pvdt.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var randomNumber = Random.nextInt(1000, 10000)
    private lateinit var playCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get all components by id
        val btnSkipPrev = findViewById<ImageButton>(R.id.ibPrevTrack)
        val btnSkipNext = findViewById<ImageButton>(R.id.ibNextTrack)
        val btnPlay = findViewById<ImageButton>(R.id.ibPlay)
        val btnChangeUser = findViewById<Button>(R.id.btnChangeUser)
        playCount = findViewById<TextView>(R.id.tvPlayCount)
        playCount.text = randomNumber.toString() + " plays"

        // set listeners
        btnSkipPrev.setOnClickListener{skipPrevClicked()}
        btnSkipNext.setOnClickListener{skipNextClicked()}
        btnPlay.setOnClickListener{playClicked()}
        btnChangeUser.setOnClickListener{changeUserName(btnChangeUser)}
    }

    private fun skipPrevClicked() {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun skipNextClicked() {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    private fun playClicked() {
        randomNumber += 1
        Log.i("PlayClicked", randomNumber.toString())
        playCount.text = randomNumber.toString() + " plays"
    }

    private fun changeUserName(btnChangeUser: Button) {
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val etUsername = findViewById<EditText>(R.id.etChangeUserName)
        if (btnChangeUser.text == "CHANGE USER") {
            // hide username text view
            tvUsername.visibility = View.GONE

            // show username edit text
            etUsername.visibility = View.VISIBLE

            // change text of 'change username' button to 'apply'
            btnChangeUser.text = "APPLY"
        } else if (btnChangeUser.text == "APPLY" && etUsername.text.toString().trim() != ""){
            // show username text view with updated username
            tvUsername.text = etUsername.text.toString()
            tvUsername.visibility = View.VISIBLE

            // hide username edit text & set to empty text value
            etUsername.text.clear()
            etUsername.visibility = View.GONE

            // change text of 'apply' button to 'change username'
            btnChangeUser.text = "CHANGE USER"
        }
    }
}