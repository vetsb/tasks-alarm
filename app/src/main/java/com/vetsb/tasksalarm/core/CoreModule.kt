package com.vetsb.tasksalarm.core

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.vetsb.tasksalarm.core.data.AndroidUtilRepositoryImpl
import com.vetsb.tasksalarm.core.data.PermissionsRepositoryImpl
import com.vetsb.tasksalarm.core.domain.PermissionsRepository
import com.vetsb.tasksalarm.core.domain.UtilInteractor
import com.vetsb.tasksalarm.core.domain.UtilRepository
import com.vetsb.tasksalarm.core.domain.usecase.has_notifications_access.HasNotificationsAccessUseCase
import com.vetsb.tasksalarm.core.domain.usecase.has_notifications_access.HasNotificationsAccessUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
    single { Gson() }

    factory { androidContext().contentResolver }
    factory { androidContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager }
    factory { androidContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }

    factory { PermissionsRepositoryImpl(androidContext()) } bind PermissionsRepository::class
    factory { AndroidUtilRepositoryImpl() } bind UtilRepository::class

    factory { HasNotificationsAccessUseCaseImpl(get()) } bind HasNotificationsAccessUseCase::class
    factory { UtilInteractor(get()) }
}