package com.vetsb.tasksalarm.crashlytics

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.vetsb.tasksalarm.crashlytics.data.FirebaseCrashlyticsRepositoryImpl
import com.vetsb.tasksalarm.crashlytics.domain.CrashlyticsRepository
import org.koin.dsl.module

val crashlyticsModule = module {
    single<CrashlyticsRepository> { FirebaseCrashlyticsRepositoryImpl(FirebaseCrashlytics.getInstance()) }
}