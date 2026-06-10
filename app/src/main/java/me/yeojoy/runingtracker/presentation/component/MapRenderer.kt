package me.yeojoy.runingtracker.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.yeojoy.runingtracker.domain.location.LocationPoint

interface MapRenderer {

    @Composable
    fun DrawMap(pathPoints: List<LocationPoint>)
}