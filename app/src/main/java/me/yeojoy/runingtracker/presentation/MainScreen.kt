package me.yeojoy.runingtracker.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.yeojoy.runingtracker.domain.model.GpsStatus
import me.yeojoy.runingtracker.domain.model.Run
import me.yeojoy.runingtracker.presentation.component.EmptyMapRenderer
import me.yeojoy.runingtracker.presentation.component.GpsStatusBadge
import me.yeojoy.runingtracker.presentation.component.LogMapRenderer
import me.yeojoy.runingtracker.presentation.component.MapRenderer
import me.yeojoy.runingtracker.presentation.component.RunItem
import me.yeojoy.runingtracker.presentation.component.SortTypeSelector
import me.yeojoy.runingtracker.ui.theme.AppTheme

@Composable
fun MainScreen(
    state: MainState,
    onAction: (MainAction) -> Unit,
    mapRenderer: MapRenderer,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.spacing.normal)
        ) {
            Text(
                text = "Running Tracker",
                style = AppTheme.typoGraphy.h1,
                color = AppTheme.colors.primary
            )
            Spacer(
                modifier = Modifier.height(AppTheme.spacing.normal)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(AppTheme.colors.surface)
            ) {
                mapRenderer.DrawMap(
                    state.displayPathPoints
                )

                GpsStatusBadge(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(AppTheme.spacing.small),
                    gpsStatus = state.gpsStatus,
                    onClick = if (state.isGpsMockingEnabled) {
                        { onAction(MainAction.ToggleGpsStatus) }
                    } else {
                        null
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(AppTheme.spacing.normal)
            )

            SortTypeSelector(
                selectedSortType = state.sortType,
                onSortTypeChanged = {
                    onAction(MainAction.ChangeSortType(it))
                }
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(vertical = AppTheme.spacing.normal)
            ) {
                items(
                    items = state.runs
                ) {
                    RunItem(
                        run = it,
                        onSelect = { onAction(MainAction.SelectRun(it)) },
                        onDelete = { onAction(MainAction.DeleteRun(it)) }
                    )
                    Spacer(modifier = Modifier.height(AppTheme.spacing.small))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    var _state by remember {
        mutableStateOf(
            MainState(
                isGpsMockingEnabled = true,
                gpsStatus = GpsStatus.Acquired,
                runs = listOf(
                    Run(
                        id = 1,
                        distanceInMeters = 5240.0,
                        timeInMillis = 1830000L,
                        timestamp = System.currentTimeMillis(),
                        avgSpeedInKMH = 10.2f
                    ),
                    Run(
                        id = 2,
                        distanceInMeters = 4240.0,
                        timeInMillis = 1330000L,
                        timestamp = System.currentTimeMillis(),
                        avgSpeedInKMH = 12.2f
                    ),
                    Run(
                        id = 3,
                        distanceInMeters = 3240.0,
                        timeInMillis = 830000L,
                        timestamp = System.currentTimeMillis(),
                        avgSpeedInKMH = 9.2f
                    ),
                )
            )
        )
    }

    MainScreen(
        state = _state,
        onAction = {
            if (it is MainAction.ToggleGpsStatus) {
                _state = _state.copy(
                    gpsStatus = when (_state.gpsStatus) {
                        GpsStatus.Acquired -> GpsStatus.Enabled
                        GpsStatus.Enabled -> GpsStatus.Disabled
                        GpsStatus.Disabled -> GpsStatus.Lost
                        GpsStatus.Lost -> GpsStatus.Acquired
                    }
                )
            } else if (it is MainAction.ChangeSortType) {
                _state = _state.copy(
                    sortType = it.sortType
                )
            }
        },
        mapRenderer = LogMapRenderer()
    )
}