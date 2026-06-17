package me.yeojoy.runingtracker.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import me.yeojoy.runingtracker.MainActivity
import me.yeojoy.runingtracker.R

class TrackingNotificationHelper(
    private val context: Context
) {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "channel_id"
        const val NOTIFICATION_CHANNEL_NAME = "channel_name"

        const val ALERT_CHANNEL_ID = "Alert_channel_id"
        const val ALERT_CHANNEL_NAME = "Alert_channel_name"
    }

    // Make a notification
    private val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as? NotificationManager

    fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            // Normal tracking channel, Quiet one
            val trackingChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW // Quiet noti
            )
            notificationManager?.createNotificationChannel(trackingChannel)

            // Urgent or warning channel with a sound
            val alertChannel = NotificationChannel(
                ALERT_CHANNEL_ID, ALERT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH // Quiet noti
            ).apply {
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
                // Light on
                enableLights(true)
                // Vibration on
                enableVibration(true)
            }
            notificationManager?.createNotificationChannel(alertChannel)
        }
    }

    fun buildTrackingNotification(): Notification {
        val notificationPendingIntent = createTrackingPendingIntent()

        return NotificationCompat.Builder(
            context, NOTIFICATION_CHANNEL_ID
        ).apply {
            setAutoCancel(false)
            setOngoing(true)
            setSmallIcon(R.drawable.ic_launcher_foreground) // FIXME
            setContentTitle("Running Tracking Noti")
            setContentText("On running ...")
            setContentIntent(notificationPendingIntent)
        }.build()
    }

    private fun createTrackingPendingIntent(requestCode: Int = 1004): PendingIntent {
        val notificationIntent = Intent(context, MainActivity::class.java).apply {
            // Not stack a same activity
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        return PendingIntent.getActivity(
            context,
            requestCode,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE // Not editable
        )
    }
}