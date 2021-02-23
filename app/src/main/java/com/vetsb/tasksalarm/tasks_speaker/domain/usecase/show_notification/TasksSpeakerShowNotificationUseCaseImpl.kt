package com.vetsb.tasksalarm.tasks_speaker.domain.usecase.show_notification

import com.vetsb.tasksalarm.tasks_speaker.domain.TasksSpeakerRepository

class TasksSpeakerShowNotificationUseCaseImpl(
    private val tasksSpeakerRepository: TasksSpeakerRepository
) : TasksSpeakerShowNotificationUseCase {

    override fun invoke() {
        tasksSpeakerRepository.showNotification()
    }
}