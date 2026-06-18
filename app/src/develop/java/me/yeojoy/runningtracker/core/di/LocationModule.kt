package me.yeojoy.runningtracker.core.di

import me.yeojoy.runningtracker.data.location.MockLocationClient
import me.yeojoy.runningtracker.domain.location.LocationClient
import org.koin.dsl.module

val locationModule = module {
    single<LocationClient> { MockLocationClient() }
}