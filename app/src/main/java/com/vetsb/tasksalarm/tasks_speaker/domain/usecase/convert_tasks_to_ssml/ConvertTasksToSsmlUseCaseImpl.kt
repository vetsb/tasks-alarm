package com.vetsb.tasksalarm.tasks_speaker.domain.usecase.convert_tasks_to_ssml

import com.vetsb.tasksalarm.tasks.domain.model.Task

class ConvertTasksToSsmlUseCaseImpl : ConvertTasksToSsmlUseCase {

    override fun invoke(tasks: List<Task>): String {
        return StringBuilder().apply {
            append("<speak>")
            append("Доброе утро!")
            append("<break time=\"1s\"/>")
            append("Вот твои задачи на сегодня")
            append("<break time=\"1s\"/>")

            tasks.forEach {
                append(it.title)
                append("<break time=\"1s\"/>")
            }

            append("<break time=\"1s\"/>")
            append("Удачи!")
            append("</speak>")
        }.toString()
    }
}