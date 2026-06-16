package me.yeojoy.runingtracker.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import me.yeojoy.runingtracker.MainActivity
import me.yeojoy.runingtracker.R

class TrackingService : Service() {

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"

        const val NOTIFICATION_CHANNEL_ID = "channel_id"
        const val NOTIFICATION_CHANNEL_NAME = "channel_name"

        const val ALERT_CHANNEL_ID = "Alert_channel_id"
        const val ALERT_CHANNEL_NAME = "Alert_channel_name"

        const val NOTIFICATION_ID = 3
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> {
                startTrackingForegroundService()
            }
            ACTION_STOP -> {
                stopTrackingForegroundService()
            }
            else -> {}
        }
        return START_STICKY
    }

    private fun startTrackingForegroundService() {
        // Make a notification
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as? NotificationManager

        // Depending on version needs to create a channel for a notification
        createNotificationChannel(notificationManager)

        val notificationIntent = Intent(this, MainActivity::class.java).apply {
            // Not stack a same activity
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val notificationPendingIntent = PendingIntent.getActivity(
            this,
            1004,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE // Not editable
        )

        val notification = NotificationCompat.Builder(
            this, NOTIFICATION_CHANNEL_ID
        ).apply {
            setAutoCancel(false)
            setOngoing(true)
            setSmallIcon(R.drawable.ic_launcher_foreground) // FIXME
            setContentTitle("Running Tracking Noti")
            setContentText("On running ...")
            setContentIntent(notificationPendingIntent)
        }.build()

        startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager?) {
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

    private fun stopTrackingForegroundService() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }
}