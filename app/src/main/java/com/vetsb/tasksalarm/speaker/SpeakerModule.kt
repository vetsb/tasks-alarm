package com.vetsb.tasksalarm.speaker

import com.vetsb.tasksalarm.speaker.data.google.GoogleCloudSpeakerRepositoryImpl
import com.vetsb.tasksalarm.speaker.domain.SpeakerRepository
import com.vetsb.tasksalarm.speaker.domain.usecase.start.StartSpeakingTextUseCase
import com.vetsb.tasksalarm.speaker.domain.usecase.start.StartSpeakingTextUseCaseImpl
import com.vetsb.tasksalarm.speaker.domain.usecase.stop.StopSpeakingTextUseCase
import com.vetsb.tasksalarm.speaker.domain.usecase.stop.StopSpeakingTextUseCaseImpl
import darren.googlecloudtts.BuildConfig
import darren.googlecloudtts.GoogleCloudTTSFactory
import darren.googlecloudtts.parameter.AudioConfig
import darren.googlecloudtts.parameter.AudioEncoding
import darren.googlecloudtts.parameter.VoiceSelectionParams
import org.koin.dsl.bind
import org.koin.dsl.module

val speakerModule = module {
    single {
        GoogleCloudTTSFactory.create(BuildConfig.API_KEY).apply {
            voiceSelectionParams = VoiceSelectionParams("ru-RU", "ru-RU-Wavenet-D")
            audioConfig = AudioConfig(AudioEncoding.MP3, 0.8F, -4F)
        }
    }

    factory { GoogleCloudSpeakerRepositoryImpl(get()) } bind SpeakerRepository::class

    factory { StartSpeakingTextUseCaseImpl(get()) } bind StartSpeakingTextUseCase::class
    factory { StopSpeakingTextUseCaseImpl(get()) } bind StopSpeakingTextUseCase::class
}