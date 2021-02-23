package com.vetsb.tasksalarm.analytics

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.vetsb.tasksalarm.analytics.data.FirebaseAnalyticsRepository
import com.vetsb.tasksalarm.analytics.domain.AnalyticsInteractor
import com.vetsb.tasksalarm.analytics.domain.AnalyticsRepository
import org.koin.dsl.module

val analyticsModule = module {
    single { Firebase.analytics }
    single<AnalyticsRepository> { FirebaseAnalyticsRepository(get()) }
    factory { AnalyticsInteractor(get()) }
}