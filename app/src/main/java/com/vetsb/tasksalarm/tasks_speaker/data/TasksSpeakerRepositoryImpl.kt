package com.vetsb.tasksalarm.tasks_speaker.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Action.SEMANTIC_ACTION_MUTE
import com.vetsb.tasksalarm.R
import com.vetsb.tasksalarm.tasks_speaker.domain.TasksSpeakerRepository
import com.vetsb.tasksalarm.tasks_speaker.presentation.StopTasksSpeakerReceiver
import java.util.*

class TasksSpeakerRepositoryImpl(
    private val context: Context,
    private val notificationManager: NotificationManager
) : TasksSpeakerRepository {

    private var notificationId: Int? = null

    override fun showNotification() {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(context.getString(R.string.tasks_speaker_channel_name))
            .setContentText(context.getString(R.string.tasks_speaker_notification_text))
            .addAction(createStopAction())
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(createStopActionPendingIntent())
            .setAutoCancel(true)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }

        notificationId = UUID.randomUUID().toString().hashCode()

        notificationId?.let {
            notificationManager.notify(
                it,
                notification
            )
        }
    }

    override fun hideNotification() {
        notificationId?.let {
            notificationManager.cancel(it)
            notificationId = null
        }
    }

    private fun createStopAction(): NotificationCompat.Action {
        return NotificationCompat.Action.Builder(
            android.R.drawable.ic_media_pause,
            context.getString(R.string.tasks_speaker_notification_action_stop),
            createStopActionPendingIntent()
        )
            .setSemanticAction(SEMANTIC_ACTION_MUTE)
            .setShowsUserInterface(false)
            .build()
    }

    private fun createStopActionPendingIntent(): PendingIntent {
        val intent = Intent(context, StopTasksSpeakerReceiver::class.java)

        return PendingIntent.getBroadcast(
            context,
            1111,
            intent,
            0
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val name = context.getString(R.string.tasks_speaker_channel_name)
        val descriptionText = context.getString(R.string.tasks_speaker_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "tasks_alarm_speaker"
    }
}