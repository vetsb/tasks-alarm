package com.vetsb.tasksalarm.tasks_speaker.domain.usecase.convert_tasks_to_ssml

import com.vetsb.tasksalarm.tasks.domain.model.Task

interface ConvertTasksToSsmlUseCase {
    operator fun invoke(tasks: List<Task>): String
}