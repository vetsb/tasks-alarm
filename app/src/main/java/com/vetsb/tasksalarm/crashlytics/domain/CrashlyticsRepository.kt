package com.vetsb.tasksalarm.crashlytics.domain

/**
 * Используется для логирования не фатальных исключений. Все записаные исключения будут
 * отображаться в консоли аналогично другим сбоям, но с пометкой на не фатальное исключение.
 * Отправка собранных данных о сбоях производится при следущем запуске приложения.
 */
interface CrashlyticsRepository {

    fun recordException(t: Throwable)

    fun setCustomProperty(key: String, value: String)
}