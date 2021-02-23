package com.vetsb.tasksalarm.tasks.domain.usecase.get_today_tasks

import com.vetsb.tasksalarm.tasks.domain.model.Task

interface GetTodayTasksUseCase {
    suspend operator fun invoke(): List<Task>
}