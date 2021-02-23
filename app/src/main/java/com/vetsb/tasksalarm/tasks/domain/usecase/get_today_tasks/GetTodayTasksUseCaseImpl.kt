package com.vetsb.tasksalarm.tasks.domain.usecase.get_today_tasks

import com.vetsb.tasksalarm.tasks.domain.TasksRepository
import com.vetsb.tasksalarm.tasks.domain.model.Task

class GetTodayTasksUseCaseImpl(
    private val tasksRepository: TasksRepository
) : GetTodayTasksUseCase {

    override suspend fun invoke(): List<Task> {
        return tasksRepository.getTodayTasks()
    }
}