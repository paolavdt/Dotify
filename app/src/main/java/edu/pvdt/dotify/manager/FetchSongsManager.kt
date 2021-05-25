package edu.pvdt.dotify.manager

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class FetchSongsManager(context: Context) {
    private val workManager: WorkManager= WorkManager.getInstance(context)

    fun fetchSongs() {
        // TODO: change this to periodic
        val request = OneTimeWorkRequestBuilder<FetchSongsWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()
        workManager.enqueue(request)
    }
}