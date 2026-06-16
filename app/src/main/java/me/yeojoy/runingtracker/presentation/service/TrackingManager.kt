package me.yeojoy.runingtracker.presentation.service

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TrackingManager {
    companion object {
        private const val TAG = "TrackingManager"
    }
    private val _state = MutableStateFlow(TrackingState())
    val state = _state.asStateFlow()

    fun updateTrackingState(isTracking: Boolean) {
        Log.i(TAG, "updateTrackingState(isTracking: $isTracking)")
        _state.update {
            TrackingState(isTracking = isTracking)
        }
    }
}