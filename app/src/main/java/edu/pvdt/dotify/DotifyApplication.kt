package edu.pvdt.dotify

import android.app.Application
import android.widget.Toast
import edu.pvdt.dotify.model.Song
import edu.pvdt.dotify.repository.DataRepository
import kotlin.random.Random

class DotifyApplication: Application() {
    lateinit var dataRepository: DataRepository
    lateinit var currSong: Song
    var songCount: Int = Random.nextInt(1000, 10000)
    var currSongPosition: Int = 0

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()
    }
}