package com.vetsb.tasksalarm.core.data

import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.vetsb.tasksalarm.core.domain.PermissionsRepository

class PermissionsRepositoryImpl(
    private val context: Context
) : PermissionsRepository {

    override fun hasNotifications(): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1 ||
                NotificationManagerCompat
                    .getEnabledListenerPackages(context)
                    .contains(context.packageName)
    }
}