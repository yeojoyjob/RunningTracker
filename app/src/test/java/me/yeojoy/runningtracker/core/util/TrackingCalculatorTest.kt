package me.yeojoy.runningtracker.core.util

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TrackingCalculatorTest {
    @Test
    fun test_calculateAverageSpeed() {
        // given
        val expected = 80f // 80 km/h
        // when
        val oneHourInMillis = 60 * 60 * 1000L
        val time1 = 1 * oneHourInMillis
        val time2 = 2 * oneHourInMillis
        val oneKiloInMeters = 1000.0
        val distanceInMeter = 80 * oneKiloInMeters // 80 km
        val distanceInMeter2 = 160 * oneKiloInMeters // 80 km

        // then
        assertEquals(
            TrackingCalculator.calculateAverageSpeed(distanceInMeter, time1),
            expected
        )
        assertEquals(
            TrackingCalculator.calculateAverageSpeed(distanceInMeter2, time2),
            expected
        )
        assertEquals(
            TrackingCalculator.calculateAverageSpeed(0.0, time2),
            0f
        )
    }
}