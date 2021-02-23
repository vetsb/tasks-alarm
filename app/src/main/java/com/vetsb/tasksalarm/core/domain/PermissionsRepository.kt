package com.vetsb.tasksalarm.core.domain

interface PermissionsRepository {
    fun hasNotifications(): Boolean
}