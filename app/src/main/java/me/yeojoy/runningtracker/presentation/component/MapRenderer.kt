package me.yeojoy.runningtracker.presentation.component

import androidx.compose.runtime.Composable
import me.yeojoy.runningtracker.domain.model.LocationPoint

interface MapRenderer {

    @Composable
    fun DrawMap(pathPoints: List<LocationPoint>)
}