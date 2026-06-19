package me.yeojoy.runningtracker.core.di

import androidx.room.Room
import me.yeojoy.runningtracker.BuildConfig
import me.yeojoy.runningtracker.data.database.RunDao
import me.yeojoy.runningtracker.data.database.RunDatabase
import me.yeojoy.runningtracker.data.location.MockLocationClient
import me.yeojoy.runningtracker.data.repository.MockRunRepositoryImpl
import me.yeojoy.runningtracker.data.repository.RoomRunRepositoryImpl
import me.yeojoy.runningtracker.domain.location.LocationClient
import me.yeojoy.runningtracker.domain.repository.RunRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

// Depending on a flavor, define on runtime
val flavorModule = module {
    if (BuildConfig.FLAVOR_environment.contains("develop")) {
        single<RunRepository> { MockRunRepositoryImpl() }
        // single<LocationClient> { MockLocationClient() }
    } else {

        single<RunDatabase> {
            if (BuildConfig.FLAVOR_environment.contains("staging")) {
                // Make a db in memory. NO FILE I/O
                Room.inMemoryDatabaseBuilder(
                    androidContext(),
                    RunDatabase::class.java
                ).build()
            } else {
                // Production
                Room.databaseBuilder(
                    androidContext(),
                    RunDatabase::class.java,
                    "running_db"
                ).build()
            }
        }

        single<RunDao> { get<RunDatabase>().getRunDao() }
        single<RunRepository> { RoomRunRepositoryImpl(get()) }
    }

    single<LocationClient> { MockLocationClient() }
}