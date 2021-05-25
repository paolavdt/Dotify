package edu.pvdt.dotify.manager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val SONG_WORK_TAG = "SONG_WORK_TAG"

class FetchSongsManager(context: Context) {
    private val workManager: WorkManager= WorkManager.getInstance(context)

    fun fetchSongs() {
        if (isSongFetchRunning()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<FetchSongsWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_WORK_TAG)
            .build()

        workManager.enqueue(request)
    }

    fun stopPeriodicFetch() {
        workManager.cancelAllWorkByTag(SONG_WORK_TAG)
    }

    private fun isSongFetchRunning(): Boolean {
        return workManager.getWorkInfosByTag(SONG_WORK_TAG).get().any{
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }
}