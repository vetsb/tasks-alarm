package com.vetsb.tasksalarm.tasks_speaker.domain.usecase.hide_notification

import com.vetsb.tasksalarm.tasks_speaker.domain.TasksSpeakerRepository

class TasksSpeakerHideNotificationUseCaseImpl(
    private val tasksSpeakerRepository: TasksSpeakerRepository
) : TasksSpeakerHideNotificationUseCase {

    override fun invoke() {
        tasksSpeakerRepository.hideNotification()
    }
}