package com.vetsb.tasksalarm.tasks_speaker

import com.vetsb.tasksalarm.tasks_speaker.data.TasksSpeakerRepositoryImpl
import com.vetsb.tasksalarm.tasks_speaker.domain.TasksSpeakerRepository
import com.vetsb.tasksalarm.tasks_speaker.domain.scenario.SpeakTodayTasksScenario
import com.vetsb.tasksalarm.tasks_speaker.domain.scenario.SpeakTodayTasksScenarioImpl
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.convert_tasks_to_ssml.ConvertTasksToSsmlUseCase
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.convert_tasks_to_ssml.ConvertTasksToSsmlUseCaseImpl
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.hide_notification.TasksSpeakerHideNotificationUseCase
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.hide_notification.TasksSpeakerHideNotificationUseCaseImpl
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.show_notification.TasksSpeakerShowNotificationUseCase
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.show_notification.TasksSpeakerShowNotificationUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val tasksSpeakerModule = module {
    single<TasksSpeakerRepository> { TasksSpeakerRepositoryImpl(androidContext(), get()) }

    factory { TasksSpeakerShowNotificationUseCaseImpl(get()) } bind TasksSpeakerShowNotificationUseCase::class
    factory { TasksSpeakerHideNotificationUseCaseImpl(get()) } bind TasksSpeakerHideNotificationUseCase::class
    factory { ConvertTasksToSsmlUseCaseImpl() } bind ConvertTasksToSsmlUseCase::class
    factory {
        SpeakTodayTasksScenarioImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    } bind SpeakTodayTasksScenario::class
}