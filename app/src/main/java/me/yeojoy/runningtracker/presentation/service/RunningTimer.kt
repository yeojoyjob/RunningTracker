package me.yeojoy.runningtracker.presentation.service

import android.os.SystemClock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RunningTimer {
    private val _timeInMillis = MutableStateFlow(0L)
    val timeInMillis = _timeInMillis.asStateFlow()

    private var startTime = 0L
    private var timerJob: Job? = null

    fun startTimer(scope: CoroutineScope) {
        startTime = SystemClock.elapsedRealtime()
        timerJob = scope.launch {
            while (true) {
                _timeInMillis.value = SystemClock.elapsedRealtime().minus(startTime)
                delay(200L)
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        _timeInMillis.value = 0L
        startTime = 0L
    }
}