package edu.pvdt.dotify.manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import android.util.Log
import edu.pvdt.dotify.DotifyApplication
import edu.pvdt.dotify.repository.DataRepository
import java.lang.Exception

class FetchSongsWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val dotifyApplication by lazy { context.applicationContext as DotifyApplication }
    private val dataRepository: DataRepository = dotifyApplication.dataRepository
    private val songNotificationManager by lazy { dotifyApplication.notificationManager }

    override suspend fun doWork(): Result {
        dataRepository.getSongs()
        songNotificationManager.publishMusicNotification()

        return Result.success()
    }
}