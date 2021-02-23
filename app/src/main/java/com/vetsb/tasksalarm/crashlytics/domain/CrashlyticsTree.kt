package com.vetsb.tasksalarm.crashlytics.domain

import android.util.Log
import timber.log.Timber

class CrashlyticsTree(
    private val crashlyticsRepository: CrashlyticsRepository
) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        crashlyticsRepository.recordException(t ?: Throwable(message))
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == Log.ASSERT
    }
}