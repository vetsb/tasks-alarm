package com.vetsb.tasksalarm.appconfig.data.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import com.vetsb.tasksalarm.BuildConfig
import com.vetsb.tasksalarm.R
import com.vetsb.tasksalarm.analytics.domain.AnalyticsInteractor
import com.vetsb.tasksalarm.appconfig.AppConfigAnalyticsConstant
import com.vetsb.tasksalarm.appconfig.domain.AppConfigRepository
import com.vetsb.tasksalarm.appconfig.domain.model.AppConfig
import kotlinx.coroutines.tasks.await

class FirebaseRemoteConfigRepositoryImpl(
    private val gson: Gson,
    private val analyticsInteractor: AnalyticsInteractor
) : AppConfigRepository {

    override val appConfig: AppConfig
        get() = Firebase.remoteConfig.all.toDomain(gson)

    override suspend fun initialize() {
        val remoteConfig = Firebase.remoteConfig
        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                AppConfigRepository.MINIMUM_FETCH_INTERVAL_IN_SECONDS_DEBUG
            } else {
                AppConfigRepository.MINIMUM_FETCH_INTERVAL_IN_SECONDS_PRODUCTION
            }
        }

        remoteConfig.run {
            setConfigSettingsAsync(settings)
            setDefaultsAsync(R.xml.firebase_defaults)
        }

        runCatching {
            remoteConfig.fetchAndActivate().await()

            analyticsInteractor.trackEvent(
                AppConfigAnalyticsConstant.EVENT_NAME,
                AppConfigAnalyticsConstant.ON_FETCHED_SUCCESSFULLY
            )
        }.onFailure {
            analyticsInteractor.trackEvent(
                AppConfigAnalyticsConstant.EVENT_NAME,
                AppConfigAnalyticsConstant.ON_FETCHED_WITH_ERROR,
                AppConfigAnalyticsConstant.ERROR_LOCALIZED_MESSAGE to it.localizedMessage
            )
        }.getOrThrow()
    }
}