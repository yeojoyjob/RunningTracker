package me.yeojoy.runningtracker.presentation

import me.yeojoy.runningtracker.domain.location.LocationPoint
import me.yeojoy.runningtracker.domain.model.GpsStatus
import me.yeojoy.runningtracker.domain.model.Run
import me.yeojoy.runningtracker.domain.model.SortType
import me.yeojoy.runningtracker.presentation.service.TrackingState

/*
    Generate a State of each screen
 */
data class MainState(
    val runs: List<Run> = listOf(),
    val sortType: SortType = SortType.DATE,
    val gpsStatus: GpsStatus = GpsStatus.Acquired,
    val trackingState: TrackingState = TrackingState(),
    val selectedRun: Run? = null,
    val displayPathPoints: List<LocationPoint> = listOf(),

    // For test
    val isGpsMockingEnabled: Boolean = true
)
