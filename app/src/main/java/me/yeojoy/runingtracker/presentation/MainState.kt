package me.yeojoy.runingtracker.presentation

import me.yeojoy.runingtracker.domain.location.LocationPoint
import me.yeojoy.runingtracker.domain.model.GpsStatus
import me.yeojoy.runingtracker.domain.model.Run
import me.yeojoy.runingtracker.domain.model.SortType
import me.yeojoy.runingtracker.presentation.service.TrackingState

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

    val isGpsMockingEnabled: Boolean = false
)
