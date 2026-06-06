package me.yeojoy.runingtracker.core

import android.app.Application
import me.yeojoy.runingtracker.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class RunningTrackerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RunningTrackerApplication)
            modules(appModule)
        }
    }
}