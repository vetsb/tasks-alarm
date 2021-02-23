package com.vetsb.tasksalarm.analytics.data

import android.os.Bundle
import android.os.Parcelable
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.vetsb.tasksalarm.analytics.domain.AnalyticsRepository
import java.io.Serializable

class FirebaseAnalyticsRepository(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsRepository {

    override fun trackScreen(screenName: String, screenClass: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
        }
    }

    override fun trackEvent(name: String, action: String, label: String?) {
        val bundle = Bundle().apply {
            putString(AnalyticsConstants.FB_PARAM_ACTION, action)
            label?.let {
                putString(AnalyticsConstants.FB_PARAM_LABEL, label)
            }
        }

        firebaseAnalytics.logEvent(name, bundle)
    }

    override fun trackEvent(name: String, action: String, vararg params: Pair<String, Any?>) {
        val bundle = Bundle().apply {
            putString(AnalyticsConstants.FB_PARAM_ACTION, action)
            for (param in params) {
                when (param.second) {
                    is String? -> putString(param.first, param.second as String?)
                    is Int -> putInt(param.first, param.second as Int)
                    is Double -> putDouble(param.first, param.second as Double)
                    is Float -> putFloat(param.first, param.second as Float)
                    is Boolean -> putBoolean(param.first, param.second as Boolean)
                    is Parcelable -> putParcelable(param.first, param.second as Parcelable)
                    is Serializable -> putSerializable(param.first, param.second as Serializable)
                }
            }
        }
        firebaseAnalytics.logEvent(name, bundle)
    }
}