package com.vetsb.tasksalarm.analytics.domain

interface AnalyticsRepository {

    fun trackScreen(screenName: String, screenClass: String)

    fun trackEvent(name: String, action: String, label: String?)

    fun trackEvent(name: String, action: String, vararg params: Pair<String, Any?>)
}