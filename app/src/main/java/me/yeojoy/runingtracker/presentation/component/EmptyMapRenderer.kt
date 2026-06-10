package me.yeojoy.runingtracker.presentation.component

import androidx.compose.runtime.Composable
import me.yeojoy.runingtracker.domain.location.LocationPoint

class EmptyMapRenderer : MapRenderer {
    @Composable
    override fun DrawMap(pathPoints: List<LocationPoint>) {

    }
}