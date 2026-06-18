package me.yeojoy.runningtracker.data.location

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.yeojoy.runningtracker.domain.location.LocationClient
import me.yeojoy.runningtracker.domain.model.LocationPoint
import kotlin.random.Random

class MockLocationClient: LocationClient {
    override fun getLocationUpdates(interval: Long): Flow<LocationPoint> = flow {
        var latitude = 37.5665
        var longitude = 126.9780

        while (true) {
            emit(LocationPoint(latitude, longitude))
            delay(interval)

            latitude += (Random.nextDouble() - 0.5) * 0.0001
            longitude += (Random.nextDouble() - 0.5) * 0.0001
        }
    }
}