package com.vetsb.tasksalarm.tasks.domain.model

import com.vetsb.tasksalarm.tasks.domain.constant.TaskPriority
import org.threeten.bp.LocalDateTime

data class Task(
    val id: Long,
    val title: String,
    val dueDate: LocalDateTime?,
    val priority: TaskPriority?
)