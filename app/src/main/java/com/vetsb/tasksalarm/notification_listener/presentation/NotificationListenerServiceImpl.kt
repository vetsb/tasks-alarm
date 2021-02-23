package com.vetsb.tasksalarm.notification_listener.presentation

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.vetsb.tasksalarm.analytics.domain.AnalyticsInteractor
import com.vetsb.tasksalarm.core.base.ext.processLifecycleScope
import com.vetsb.tasksalarm.notification_listener.NotificationListenerAnalyticsConstant
import com.vetsb.tasksalarm.tasks_speaker.domain.scenario.SpeakTodayTasksScenario
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationListenerServiceImpl : NotificationListenerService(), KoinComponent {

    private val speakTodayTasksScenario by inject<SpeakTodayTasksScenario>()

    private val analyticsInteractor by inject<AnalyticsInteractor>()

    override fun onListenerConnected() {
        super.onListenerConnected()

        analyticsInteractor.trackEvent(
            NotificationListenerAnalyticsConstant.EVENT_NAME,
            NotificationListenerAnalyticsConstant.CONNECTED
        )
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()

        analyticsInteractor.trackEvent(
            NotificationListenerAnalyticsConstant.EVENT_NAME,
            NotificationListenerAnalyticsConstant.DISCONNECTED
        )
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        analyticsInteractor.trackEvent(
            NotificationListenerAnalyticsConstant.EVENT_NAME,
            NotificationListenerAnalyticsConstant.NOTIFICATION_POSTED
        )

        if (sbn == null) {
            return
        }

        if (sbn.isTickTickDailyReminder()) {
            analyticsInteractor.trackEvent(
                NotificationListenerAnalyticsConstant.EVENT_NAME,
                NotificationListenerAnalyticsConstant.TICK_TICK_DAILY_REMINDER_POSTED
            )

            processLifecycleScope.launch {
                speakTodayTasksScenario()
            }
        }
    }
}