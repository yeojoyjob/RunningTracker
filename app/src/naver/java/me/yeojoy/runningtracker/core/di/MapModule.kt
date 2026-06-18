package me.yeojoy.runningtracker.core.di

import me.yeojoy.runningtracker.presentation.component.MapRenderer
import me.yeojoy.runningtracker.presentation.component.NaverMapRenderer
import org.koin.dsl.module

val mapModule = module {
    single<MapRenderer> { NaverMapRenderer() }
}