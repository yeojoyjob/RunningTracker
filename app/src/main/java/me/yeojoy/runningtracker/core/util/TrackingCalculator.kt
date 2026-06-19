package me.yeojoy.runningtracker.core.util

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

object TrackingCalculator {

    /**
     *
     */
    fun calculateDistanceInMeters(
        lat1: Double, lng1: Double,
        lat2: Double, lng2: Double
    ): Double {
        val r = 6371000.0 // Earth radius in meters
        val dLat = Math.toRadians(lat2 - lat1)
        val dLng = Math.toRadians(lng2 - lng1)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLng / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }

    // Return km/h
    fun calculateAverageSpeed(
        distanceInMeters: Double,
        timeInMillis: Long
    ): Float {
        val timeInSeconds = timeInMillis / 1000f

        return if (timeInSeconds > 0f) {
            ((distanceInMeters * 3600) / (timeInSeconds * 1000)).toFloat()
        } else {
            0f
        }
    }
}