package com.vetsb.tasksalarm.appconfig.domain

import com.vetsb.tasksalarm.appconfig.domain.model.AppConfig

interface AppConfigRepository {
    val appConfig: AppConfig

    suspend fun initialize()

    companion object {
        const val MINIMUM_FETCH_INTERVAL_IN_SECONDS_PRODUCTION = 3600L
        const val MINIMUM_FETCH_INTERVAL_IN_SECONDS_DEBUG = 1L
    }
}