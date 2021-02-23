package com.vetsb.tasksalarm.tasks.domain

import com.vetsb.tasksalarm.tasks.domain.model.Task

interface TasksRepository {
    suspend fun getTodayTasks(): List<Task>
}