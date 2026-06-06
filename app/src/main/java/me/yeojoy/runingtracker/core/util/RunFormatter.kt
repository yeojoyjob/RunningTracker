package me.yeojoy.runingtracker.core.util

import java.util.Locale
import java.util.concurrent.TimeUnit

object RunFormatter {
    fun formatDistance(meters: Double): String {
        return String.format(Locale.getDefault(), "%.2f km", meters / 1000)
    }

    fun formatTime(ms: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(ms)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(ms) % 60
        val secs = TimeUnit.MILLISECONDS.toSeconds(ms) % 60

        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, secs)
    }

    fun formatSpeed(speedKMH: Float): String {
        return String.format(Locale.getDefault(), "%.1f km/h", speedKMH)
    }
}