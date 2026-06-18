package me.yeojoy.runningtracker

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import me.yeojoy.runningtracker.presentation.MainRoot
import me.yeojoy.runningtracker.ui.theme.RunningTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            // Intended set dark theme even if the OS has a light theme
            statusBarStyle = SystemBarStyle.dark(
                Color.TRANSPARENT
            )
        )
        setContent {
            RunningTrackerTheme {
                MainRoot()
            }
        }
    }
}
