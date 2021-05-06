package edu.pvdt.dotify

import android.app.Application
import android.widget.Toast
import kotlin.random.Random

class DotifyApplication: Application() {
    var songCount: Int = Random.nextInt(1000, 10000)

    override fun onCreate() {
        super.onCreate()

//        Toast.makeText(this, "Dotify application has been booted", Toast.LENGTH_SHORT).show()
    }
}