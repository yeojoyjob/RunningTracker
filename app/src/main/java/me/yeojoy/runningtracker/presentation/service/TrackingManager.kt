package me.yeojoy.runningtracker.presentation.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.yeojoy.runningtracker.core.util.TrackingCalculator
import me.yeojoy.runningtracker.domain.location.LocationClient
import me.yeojoy.runningtracker.domain.model.LocationPoint

class TrackingManager(
    private val locationClient: LocationClient,
    private val runningTimer: RunningTimer,
) {
    companion object {
        private const val TAG = "TrackingManager"
    }
    private val _state = MutableStateFlow(TrackingState())
    val state = _state.asStateFlow()

    private var timerJob: Job? = null
    private var locationJob: Job? = null

    private var lastLocation: LocationPoint? = null
    private var distanceInMeters = 0.0

    fun startTracking(scope: CoroutineScope) {
        _state.update {
            TrackingState(isTracking = true)
        }
        startLocationTracking(scope)
        startTimer(scope)
    }

    fun stopTracking() {
        _state.update {
            TrackingState(isTracking = false)
        }
        timerJob?.cancel()
        timerJob = null
        locationJob?.cancel()
        locationJob = null
        runningTimer.stopTimer()
        lastLocation = null
        distanceInMeters = 0.0
    }

    private fun startTimer(scope: CoroutineScope) {
        runningTimer.startTimer(scope)
        timerJob?.cancel()
        timerJob = runningTimer.timeInMillis
            .onEach { time ->
                _state.update {
                    if (it.isTracking) {
                        it.copy(timeInMillis = time)
                    } else {
                        it
                    }
                }
            }
            .launchIn(scope)
    }

    private fun startLocationTracking(scope: CoroutineScope) {
        locationJob?.cancel()
        locationJob = locationClient.getLocationUpdates(1000L).onEach { point ->
            _state.update { currentState ->
                if (!currentState.isTracking) return@update currentState

                distanceInMeters += lastLocation?.let { last ->
                    TrackingCalculator.calculateDistanceInMeters(
                        last.latitude, last.longitude,
                        point.latitude, point.longitude
                    )
                } ?: 0.0

                val averageSpeed = TrackingCalculator.calculateAverageSpeed(
                    distanceInMeters,
                    currentState.timeInMillis
                )
                lastLocation = point

                currentState.copy(
                    pathPoints = (currentState.pathPoints + point).toList(),
                    distanceInMeters = distanceInMeters,
                    avgSpeedInKMH = averageSpeed
                )
            }
        }.launchIn(scope)
    }
}