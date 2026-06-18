package me.yeojoy.runningtracker.presentation.component

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import me.yeojoy.runningtracker.domain.location.LocationPoint
import me.yeojoy.runningtracker.ui.theme.AppTheme

class NaverMapRenderer: MapRenderer {

    @Composable
    override fun DrawMap(pathPoints: List<LocationPoint>) {
        // TODO("Not yet implemented")
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current

        var textViewOnScreen by remember {
            mutableStateOf<TextView?>(value = null)
        }
        val textView = remember {
            TextView(context).apply {
                textViewOnScreen = this
                text = "--- NaverMapRenderer ---"
                setTextColor(Color.White.toArgb())
            }
        }

        // To show a non-composable view into a composable view
        AndroidView(
            factory = { textView },
            modifier = Modifier.fillMaxSize()
        )

        // Do something need to do on onResume, onStart, onPause or onStop
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_CREATE -> {
                        textView.append("\nonCreate()")
                    }
                    Lifecycle.Event.ON_START -> {
                        textView.append("\nonStart()")
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        textView.append("\nonResume()")
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        textView.append("\nonPause()")
                    }
                    Lifecycle.Event.ON_STOP -> {
                        textView.append("\nonStop()")
                    }
                    Lifecycle.Event.ON_DESTROY -> {
                        textView.append("\nonDestroyed()")
                    }
                    Lifecycle.Event.ON_ANY -> {
                        textView.append("\nonAny()")
                    }
                }
                // Observe lifecycle
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                // Release lifecycleObserver
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }
}