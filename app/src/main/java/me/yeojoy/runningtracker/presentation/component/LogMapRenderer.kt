package me.yeojoy.runningtracker.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.yeojoy.runningtracker.domain.location.LocationPoint
import me.yeojoy.runningtracker.ui.theme.AppTheme

class LogMapRenderer : MapRenderer {
    @Composable
    override fun DrawMap(pathPoints: List<LocationPoint>) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .padding(AppTheme.spacing.large)
        ) {
            item {
                Text(
                    text = "--- Map logs (Mock) ---",
                    color = Color.White
                )
            }
            items(pathPoints.takeLast(5)) {
                Text(
                    text = "Latitude: ${it.latitude} Longitude: ${it.longitude}",
                    color = Color.White
                )
            }
        }
    }
}