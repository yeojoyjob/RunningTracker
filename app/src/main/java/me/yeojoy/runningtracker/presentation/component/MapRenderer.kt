package me.yeojoy.runningtracker.presentation.component

import androidx.compose.runtime.Composable
import me.yeojoy.runningtracker.domain.location.LocationPoint

interface MapRenderer {

    @Composable
    fun DrawMap(pathPoints: List<LocationPoint>)
}