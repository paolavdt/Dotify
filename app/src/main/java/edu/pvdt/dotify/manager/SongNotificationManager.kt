package edu.pvdt.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ericchee.songdataprovider.SongDataProvider
import edu.pvdt.dotify.MainActivity
import edu.pvdt.dotify.R
import edu.pvdt.dotify.SONG_INFO_KEY
import kotlin.random.Random

private const val NEW_MUSIC_CHANNEL_ID = "NEW_MUSIC_CHANNEL_ID"

class SongNotificationManager(
    private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        initNotificationChannels()
    }

    private fun initNotificationChannels() {
        initNewUploadedMusicChannel()
    }

    private fun initNewUploadedMusicChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Info about the channel
            val name = context.getString(R.string.new_music)
            val descriptionText = context.getString(R.string.new_music_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create channel object
            val channel = NotificationChannel(NEW_MUSIC_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Tell the Android OS to create a channel
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun publishMusicNotification() {
        // get random song
        val randSong = SongDataProvider.getAllSongs().random()

        // define the intent when user taps on notification
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(SONG_INFO_KEY, randSong)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // create notification
        val builder = NotificationCompat.Builder(context, NEW_MUSIC_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .setContentTitle(context.getString(R.string.notif_title, randSong.artist))
            .setContentText(context.getString(R.string.notif_text, randSong.title))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(notificationManager) {
            val notificationID = Random.nextInt()
            notify(notificationID, builder.build())
        }
    }
}