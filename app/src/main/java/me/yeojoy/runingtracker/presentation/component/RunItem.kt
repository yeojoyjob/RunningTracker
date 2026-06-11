package me.yeojoy.runingtracker.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.yeojoy.runingtracker.core.util.RunFormatter
import me.yeojoy.runingtracker.domain.model.Run
import me.yeojoy.runingtracker.presentation.MainAction
import me.yeojoy.runingtracker.ui.theme.AppTheme
import me.yeojoy.runingtracker.ui.theme.RunningTrackerTheme

@Composable
fun RunItem(
    modifier: Modifier = Modifier,
    run: Run,
    onSelect: () -> Unit = {
        MainAction.SelectRun(run)
    },
    onDelete: () -> Unit = {
        MainAction.DeleteRun(run)
    },
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = AppTheme.spacing.normal)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                RunInfoRow(
                    icon = Icons.Default.History,
                    label = "Distance",
                    value = RunFormatter.formatDistance(run.distanceInMeters)
                )
                Spacer(modifier = Modifier.height(AppTheme.spacing.tiny))
                RunInfoRow(
                    icon = Icons.Default.Timer,
                    label = "Time",
                    value = RunFormatter.formatTime(run.timeInMillis)
                )
                Spacer(modifier = Modifier.height(AppTheme.spacing.tiny))
                RunInfoRow(
                    icon = Icons.Default.Speed,
                    label = "Avg Speed",
                    value = RunFormatter.formatSpeed(run.avgSpeedInKMH)
                )
            }

            IconButton(
                onClick = onDelete,
                modifier = Modifier.clip(CircleShape)
                    .background(color = AppTheme.colors.error.copy(alpha = 0.1f))
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = AppTheme.colors.error
                )
            }
        }
    }
}

@Composable
fun RunInfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = AppTheme.colors.primary
        )
        Spacer(modifier = Modifier.width(AppTheme.spacing.small))
        Text(
            text = "$label: ",
            style = AppTheme.typoGraphy.caption,
            color = AppTheme.colors.secondaryText
        )
        Spacer(modifier = Modifier.width(AppTheme.spacing.tiny))
        Text(
            text = value,
            style = AppTheme.typoGraphy.body2,
            color = AppTheme.colors.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true) // showBackground: make a white background for the preview
@Composable
private fun RunItemPreview() {
    RunningTrackerTheme {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            RunItem(
                run = Run(
                    id = 1,
                    distanceInMeters = 5240.0,
                    timeInMillis = 1830000L,
                    timestamp = System.currentTimeMillis(),
                    avgSpeedInKMH = 10.2f
                )
            )
        }
    }
}