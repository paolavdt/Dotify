package edu.pvdt.dotify

import android.app.Application
import android.widget.Toast
import edu.pvdt.dotify.repository.DataRepository
import kotlin.random.Random

class DotifyApplication: Application() {
    lateinit var dataRepository: DataRepository
    var songCount: Int = Random.nextInt(1000, 10000)

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()
    }
}