package me.yeojoy.runningtracker.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.yeojoy.runningtracker.domain.model.SortType
import me.yeojoy.runningtracker.ui.theme.AppTheme

@Composable
fun SortTypeSelector(
    modifier: Modifier = Modifier,
    selectedSortType: SortType,
    onSortTypeChanged: (SortType) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
    ) {
        SortType.entries.forEach { sortType ->
            val isSelected = sortType == selectedSortType
            SortTypeItem(
                sortType = sortType,
                isSelected = isSelected,
                modifier = modifier.clickable {
                    onSortTypeChanged(sortType)
                },
            )
        }
    }
}

@Composable
fun SortTypeItem(
    modifier: Modifier = Modifier,
    sortType: SortType,
    isSelected: Boolean,
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) {
            AppTheme.colors.primary
        } else {
            AppTheme.colors.surface
        },
        contentColor = if (isSelected) AppTheme.colors.onPrimary else AppTheme.colors.onSurface,
        modifier = modifier.padding(vertical = 4.dp),
    ) {
        Text(
            text = sortType.name.replace("_", " "),
            style = AppTheme.typoGraphy.caption,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SortTypeItemPreview() {
    Row(Modifier.padding(vertical = 4.dp)) {
        SortTypeItem(sortType = SortType.AVG_SPEED, isSelected = true)
        SortTypeItem(sortType = SortType.RUNNING_TIME, isSelected = false)
    }
}

@Preview(showBackground = true)
@Composable
private fun SortTypeSelectorPreview() {
    val selectedSortType = remember { mutableStateOf(SortType.DATE) }

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        SortTypeSelector(
            selectedSortType = selectedSortType.value,
            onSortTypeChanged = {
                selectedSortType.value = it
            }
        )
    }
}