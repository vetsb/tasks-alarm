package com.vetsb.tasksalarm.root.presentation

import androidx.lifecycle.ViewModel
import com.vetsb.tasksalarm.analytics.domain.AnalyticsInteractor
import com.vetsb.tasksalarm.core.domain.usecase.has_notifications_access.HasNotificationsAccessUseCase
import com.vetsb.tasksalarm.root.MainAnalyticsConstant
import com.vetsb.tasksalarm.root.presentation.command.RequestNotificationPermissionCommand
import com.vetsb.tasksalarm.root.presentation.model.MainUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val hasNotificationsAccessUseCase: HasNotificationsAccessUseCase,
    private val analyticsInteractor: AnalyticsInteractor
) : ViewModel() {

    val uiModel = MutableStateFlow(MainUiModel())
    val command = MutableSharedFlow<Any>(replay = 1)

    init {
        uiModel.value = uiModel.value.copy(
            isPermissionChecked = hasNotificationsAccessUseCase()
        )
    }

    fun onPermissionContainerClick() {
        if (uiModel.value.isPermissionChecked) {
            analyticsInteractor.trackEvent(
                MainAnalyticsConstant.EVENT_NAME,
                MainAnalyticsConstant.CLICK_GRANTED_CONTAINER
            )
        } else {
            analyticsInteractor.trackEvent(
                MainAnalyticsConstant.EVENT_NAME,
                MainAnalyticsConstant.CLICK_NOT_GRANTED_CONTAINER
            )

            if (!uiModel.value.isPermissionChecked) {
                command.tryEmit(RequestNotificationPermissionCommand)
            }
        }
    }

    fun onRequestNotificationsPermissionResult() {
        uiModel.value = uiModel.value.copy(
            isPermissionChecked = hasNotificationsAccessUseCase()
        )

        analyticsInteractor.trackEvent(
            MainAnalyticsConstant.EVENT_NAME,
            MainAnalyticsConstant.ON_PERMISSION_RESULT
        )
    }
}