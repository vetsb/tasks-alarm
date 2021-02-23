package com.vetsb.tasksalarm.core.domain.usecase.has_notifications_access

import com.vetsb.tasksalarm.core.domain.PermissionsRepository

class HasNotificationsAccessUseCaseImpl(
    private val permissionsRepository: PermissionsRepository
) : HasNotificationsAccessUseCase {

    override fun invoke(): Boolean {
        return permissionsRepository.hasNotifications()
    }
}