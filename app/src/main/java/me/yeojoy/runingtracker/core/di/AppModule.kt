package me.yeojoy.runingtracker.core.di

import me.yeojoy.runingtracker.data.MockRunRepositoryImpl
import me.yeojoy.runingtracker.domain.repository.RunRepository
import me.yeojoy.runingtracker.domain.use_case.DeleteRunUseCase
import me.yeojoy.runingtracker.domain.use_case.GetRunsUseCase
import me.yeojoy.runingtracker.domain.use_case.SaveRunUseCase
import org.koin.dsl.module

val appModule = module {
    // singleton object
    single<RunRepository> { MockRunRepositoryImpl() }

    single { DeleteRunUseCase(get()) }
    single { SaveRunUseCase(get()) }
    single { GetRunsUseCase(get()) }
}