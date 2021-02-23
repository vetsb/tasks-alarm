package com.vetsb.tasksalarm.notification_listener.presentation

import android.service.notification.StatusBarNotification

private const val TICK_TICK_PACKAGE_NAME = "com.ticktick.task"
private const val DAILY_REMINDER_CATEGORY = "reminder"
private const val DAILY_REMINDER_GROUP = "com.ticktick.task.group_daily_reminder"

fun StatusBarNotification.isTickTickDailyReminder(): Boolean {
    return packageName == TICK_TICK_PACKAGE_NAME &&
            notification?.category == DAILY_REMINDER_CATEGORY &&
            notification?.group == DAILY_REMINDER_GROUP &&
            notification?.tickerText.isNullOrBlank()
}