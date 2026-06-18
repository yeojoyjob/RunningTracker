package me.yeojoy.runningtracker.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class RunningTrackerColors(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val secondaryText: Color,
    val accent: Color,
    val error: Color,
    val success: Color,
    val warning: Color,
)

val DarkColorPalette = RunningTrackerColors(
    primary = Color(0xff00e5ff),
    onPrimary = Color(0xff000000),
    background = Color(0xff0f172a),
    onBackground = Color(0xfff8fafc),
    surface = Color(0xff1e293b),
    onSurface = Color(0xfff1f5f9),
    secondaryText = Color(0xff94a3b8),
    accent = Color(0xffff4081),
    error = Color(0xffef4444),
    success = Color(0xff10b981),
    warning = Color(0xfff59e0b),
)

// Make it accessible at any location
val LocalRunningTrackerColors = staticCompositionLocalOf {
    DarkColorPalette
}