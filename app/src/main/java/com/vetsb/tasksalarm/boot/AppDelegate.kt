package com.vetsb.tasksalarm.boot

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vetsb.tasksalarm.BuildConfig
import com.vetsb.tasksalarm.analytics.analyticsModule
import com.vetsb.tasksalarm.appconfig.appConfigModule
import com.vetsb.tasksalarm.appconfig.domain.usecase.initialize.InitializeAppConfigUseCase
import com.vetsb.tasksalarm.core.base.ext.launchSafe
import com.vetsb.tasksalarm.core.base.ext.processLifecycleScope
import com.vetsb.tasksalarm.core.coreModule
import com.vetsb.tasksalarm.crashlytics.crashlyticsModule
import com.vetsb.tasksalarm.crashlytics.domain.CrashlyticsTree
import com.vetsb.tasksalarm.root.rootModule
import com.vetsb.tasksalarm.speaker.speakerModule
import com.vetsb.tasksalarm.tasks.tasksModule
import com.vetsb.tasksalarm.tasks_speaker.tasksSpeakerModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppDelegate : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        startKoin()
    }

    override fun onCreate() {
        super.onCreate()

        initTimber()

        AndroidThreeTen.init(this)

        processLifecycleScope.launchSafe {
            get<InitializeAppConfigUseCase>()()
        }
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@AppDelegate)
            modules(
                crashlyticsModule,
                coreModule,
                analyticsModule,
                appConfigModule,
                tasksModule,
                speakerModule,
                tasksSpeakerModule,
                rootModule
            )
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashlyticsTree(get()))
        }
    }
}