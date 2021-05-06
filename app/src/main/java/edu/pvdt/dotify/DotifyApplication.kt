package edu.pvdt.dotify

import android.app.Application
import android.widget.Toast

class DotifyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Toast.makeText(this, "Dotify application has been booted", Toast.LENGTH_SHORT).show()
    }
}