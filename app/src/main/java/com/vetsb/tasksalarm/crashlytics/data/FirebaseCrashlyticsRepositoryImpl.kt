package com.vetsb.tasksalarm.crashlytics.data

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.vetsb.tasksalarm.crashlytics.domain.CrashlyticsRepository

class FirebaseCrashlyticsRepositoryImpl(
    private val firebaseCrashlytics: FirebaseCrashlytics
) : CrashlyticsRepository {

    override fun recordException(t: Throwable) {
        firebaseCrashlytics.recordException(t)
    }

    override fun setCustomProperty(key: String, value: String) {
        firebaseCrashlytics.setCustomKey(key, value)
    }
}