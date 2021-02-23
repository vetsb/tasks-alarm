package com.vetsb.tasksalarm.boot.presentation

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vetsb.tasksalarm.analytics.domain.AnalyticsInteractor
import com.vetsb.tasksalarm.boot.BootAnalyticsConstant
import com.vetsb.tasksalarm.notification_listener.presentation.NotificationListenerServiceImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootReceiver : BroadcastReceiver(), KoinComponent {

    private val analyticsInteractor by inject<AnalyticsInteractor>()

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        val serviceIntent = Intent(context, NotificationListenerServiceImpl::class.java)
        context?.startService(serviceIntent)

        analyticsInteractor.trackEvent(
            BootAnalyticsConstant.EVENT_NAME,
            BootAnalyticsConstant.RECEIVE_BOOT_EVENT
        )
    }
}