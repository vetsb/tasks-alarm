package com.vetsb.tasksalarm.appconfig

import com.vetsb.tasksalarm.appconfig.data.firebase.FirebaseRemoteConfigRepositoryImpl
import com.vetsb.tasksalarm.appconfig.domain.AppConfigRepository
import com.vetsb.tasksalarm.appconfig.domain.usecase.get_config.GetAppConfigUseCase
import com.vetsb.tasksalarm.appconfig.domain.usecase.get_config.GetAppConfigUseCaseImpl
import com.vetsb.tasksalarm.appconfig.domain.usecase.initialize.InitializeAppConfigUseCase
import com.vetsb.tasksalarm.appconfig.domain.usecase.initialize.InitializeAppConfigUseCaseImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val appConfigModule = module {
    single<AppConfigRepository> { FirebaseRemoteConfigRepositoryImpl(get(), get()) }

    factory { InitializeAppConfigUseCaseImpl(get()) } bind InitializeAppConfigUseCase::class
    factory { GetAppConfigUseCaseImpl(get()) } bind GetAppConfigUseCase::class
}