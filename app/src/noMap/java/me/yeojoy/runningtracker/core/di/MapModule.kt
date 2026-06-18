package me.yeojoy.runningtracker.core.di

import me.yeojoy.runningtracker.presentation.component.LogMapRenderer
import me.yeojoy.runningtracker.presentation.component.MapRenderer
import org.koin.dsl.module

val mapModule = module {
    single<MapRenderer> { LogMapRenderer() }
}