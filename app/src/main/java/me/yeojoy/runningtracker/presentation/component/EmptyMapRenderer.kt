package me.yeojoy.runningtracker.presentation.component

import androidx.compose.runtime.Composable
import me.yeojoy.runningtracker.domain.location.LocationPoint

class EmptyMapRenderer : MapRenderer {
    @Composable
    override fun DrawMap(pathPoints: List<LocationPoint>) {

    }
}