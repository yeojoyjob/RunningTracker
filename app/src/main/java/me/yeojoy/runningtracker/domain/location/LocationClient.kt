package me.yeojoy.runningtracker.domain.location

import kotlinx.coroutines.flow.Flow
import me.yeojoy.runningtracker.domain.model.LocationPoint

interface LocationClient {
    fun getLocationUpdates(interval: Long): Flow<LocationPoint>
}