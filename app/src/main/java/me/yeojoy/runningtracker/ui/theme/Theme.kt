package me.yeojoy.runningtracker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun RunningTrackerTheme(
    colors: RunningTrackerColors = DarkColorPalette,
    typoGraphy: RunningTrackerTypoGraphy = DefaultTypoGraphy,
    spacing: RunningTrackerSpacing = RunningTrackerSpacing(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalRunningTrackerColors provides colors, // LocalRunningTrackerColor.provides(colors)
        LocalRunningTrackerTypoGraphy provides typoGraphy,
        LocalRunningTrackerSpacing provides spacing,

    ) {
        // All composable functions can access colors, typoGraphy, and spacing
        content()
    }
}

object AppTheme {
    val colors: RunningTrackerColors
        @Composable
        get() = LocalRunningTrackerColors.current

    val typoGraphy: RunningTrackerTypoGraphy
        @Composable
        get() = LocalRunningTrackerTypoGraphy.current

    val spacing: RunningTrackerSpacing
        @Composable
        get() = LocalRunningTrackerSpacing.current
}