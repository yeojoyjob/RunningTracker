package me.yeojoy.runningtracker.presentation.service

import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import org.koin.android.ext.android.inject

class TrackingService : LifecycleService() {

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"

        const val NOTIFICATION_ID = 3
    }

    private val trackingManager: TrackingManager by inject() // inject by koin into field
    private val notificationTrackingHelper: TrackingNotificationHelper by inject()

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

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
        notificationTrackingHelper.createNotificationChannels()

        // Depending on version needs to create a channel for a notification
        val notification = notificationTrackingHelper.buildTrackingNotification()

        startForeground(NOTIFICATION_ID, notification)
        trackingManager.updateTrackingState(isTracking = true)
    }

    private fun stopTrackingForegroundService() {
        trackingManager.updateTrackingState(isTracking = false)

        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }
}