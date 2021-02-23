package com.vetsb.tasksalarm.root.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vetsb.tasksalarm.R
import com.vetsb.tasksalarm.analytics.domain.AnalyticsInteractor
import com.vetsb.tasksalarm.core.base.ext.observe
import com.vetsb.tasksalarm.root.MainAnalyticsConstant
import com.vetsb.tasksalarm.root.presentation.command.RequestNotificationPermissionCommand
import com.vetsb.tasksalarm.tasks_speaker.domain.scenario.SpeakTodayTasksScenario
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val analyticsInteractor by inject<AnalyticsInteractor>()

    private val speakTodayTasksScenario by inject<SpeakTodayTasksScenario>()

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        viewModel.onRequestNotificationsPermissionResult()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            delay(2000L)

            speakTodayTasksScenario()
        }

        analyticsInteractor.trackScreen(MainAnalyticsConstant.SCREEN_NAME, javaClass.simpleName)

        vPermissionContainer.setOnClickListener {
            viewModel.onPermissionContainerClick()
        }

        viewModel.uiModel.observe(this) {
            vPermissionCheckbox.isChecked = it.isPermissionChecked
        }

        viewModel.command.observe(this) {
            when (it) {
                is RequestNotificationPermissionCommand -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                        requestNotificationPermission()
                    }
                }
            }

            viewModel.command.resetReplayCache()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun requestNotificationPermission() {
        val notificationListenerPermissionIntent = Intent(
            Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
        )

        notificationPermissionLauncher.launch(notificationListenerPermissionIntent)

        analyticsInteractor.trackEvent(
            MainAnalyticsConstant.EVENT_NAME,
            MainAnalyticsConstant.OPEN_ACCESS_PERMISSION_SCREEN
        )
    }
}