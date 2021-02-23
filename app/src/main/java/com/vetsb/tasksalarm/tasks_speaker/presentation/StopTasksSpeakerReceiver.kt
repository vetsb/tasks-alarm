package com.vetsb.tasksalarm.tasks_speaker.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vetsb.tasksalarm.speaker.domain.usecase.stop.StopSpeakingTextUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StopTasksSpeakerReceiver : BroadcastReceiver(), KoinComponent {

    private val stopSpeakingTextUseCase by inject<StopSpeakingTextUseCase>()

    override fun onReceive(context: Context?, intent: Intent?) {
        stopSpeakingTextUseCase()
    }
}