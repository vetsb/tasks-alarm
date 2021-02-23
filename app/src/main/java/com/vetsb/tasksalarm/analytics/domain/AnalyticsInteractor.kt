package com.vetsb.tasksalarm.analytics.domain

open class AnalyticsInteractor(
    private val analyticsRepository: AnalyticsRepository
) {

    fun trackScreen(screenName: String, className: String) {
        analyticsRepository.trackScreen(screenName, className)
    }

    fun trackEvent(eventName: String, eventAction: String, eventLabel: String) {
        analyticsRepository.trackEvent(eventName, eventAction, eventLabel)
    }

    fun trackEvent(name: String, action: String) {
        analyticsRepository.trackEvent(name, action, null)
    }

    fun trackEvent(name: String, action: String, vararg params: Pair<String, Any?>) {
        analyticsRepository.trackEvent(name, action, *params)
    }
}