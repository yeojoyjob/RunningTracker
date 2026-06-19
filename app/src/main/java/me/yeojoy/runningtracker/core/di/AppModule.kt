package me.yeojoy.runningtracker.core.di

import me.yeojoy.runningtracker.domain.use_case.DeleteRunUseCase
import me.yeojoy.runningtracker.domain.use_case.GetRunsUseCase
import me.yeojoy.runningtracker.domain.use_case.SaveRunUseCase
import me.yeojoy.runningtracker.presentation.MainViewModel
import me.yeojoy.runningtracker.presentation.service.RunningTimer
import me.yeojoy.runningtracker.presentation.service.TrackingManager
import me.yeojoy.runningtracker.presentation.service.TrackingNotificationHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // singleton object
    single { DeleteRunUseCase(get()) }
    single { SaveRunUseCase(get()) }
    single { GetRunsUseCase(get()) }

    single { TrackingNotificationHelper(androidContext()) }

    single { RunningTimer() }
    single {
        TrackingManager(
        get(),
        get()
        )
    }

    viewModel {
        MainViewModel(
            saveRunUseCase = get(),
            deleteRunUseCase = get(),
            getRunsUseCase = get(),
            trackingManager = get()
        )
    }
}