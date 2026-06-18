package me.yeojoy.runningtracker.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import me.yeojoy.runningtracker.presentation.component.MapRenderer
import me.yeojoy.runningtracker.presentation.service.TrackingService
import me.yeojoy.runningtracker.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun MainRoot(
    viewModel: MainViewModel = koinViewModel(),
    mapRenderer: MapRenderer = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val permissions = mutableListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    ).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            add(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val allGranted = results.values.all { it }
        if (allGranted) {
            // Granted

        } else {
            // Denied, add an AlertDialog to let users know why they are needed
        }
    }

    // Collect event, it's good to handle events
    LaunchedEffect(true /* Just run this once with `true` */) {
        viewModel.event.collect { event ->
            when (event) {
                is MainEvent.PermissionRequired -> {

                }
                MainEvent.RunSaved -> {
                    // Show a snackbar
                    launch {
                        snackbarHostState.showSnackbar("Saved...")
                    }
                }
                is MainEvent.ShowSnackBar -> {

                }
                MainEvent.StartTracking -> {
                    val hasAllPermissions = permissions.all {
                        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                    }
                    if (hasAllPermissions) {
                        val intent = Intent(context, TrackingService::class.java).apply {
                            action = TrackingService.ACTION_START
                        }
                        ContextCompat.startForegroundService(context, intent)
                    } else {
                        permissionLauncher.launch(permissions.toTypedArray())
                    }
                }
                MainEvent.StopTracking -> {
                    val intent = Intent(context, TrackingService::class.java).apply {
                        action = TrackingService.ACTION_STOP
                    }
                    context.startService(intent)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            AnimatedVisibility(
                visible = !state.trackingState.isTracking,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut(),
            ) {
                FloatingActionButton(
                    onClick = {
                        viewModel.onAction(MainAction.ClickRun)
                    },
                    containerColor = AppTheme.colors.primary,
                    contentColor = AppTheme.colors.onPrimary,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Start running"
                    )
                }
            }
        },
        // Exterior of the app color
        containerColor = AppTheme.colors.background
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            MainScreen(
                state = state,
                onAction = {
                    viewModel.onAction(it)
                },
                mapRenderer = mapRenderer
            )
        }
    }
}