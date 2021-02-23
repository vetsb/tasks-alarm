package com.vetsb.tasksalarm.tasks.data.ticktick

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import com.vetsb.tasksalarm.core.base.constant.MILLIS_IN_SECOND
import com.vetsb.tasksalarm.core.base.ext.toDefaultZoned
import com.vetsb.tasksalarm.core.base.ext.toStartDay
import com.vetsb.tasksalarm.tasks.data.ticktick.constant.TickTickCursorColumn
import com.vetsb.tasksalarm.tasks.domain.TasksRepository
import com.vetsb.tasksalarm.tasks.domain.constant.TaskPriority
import com.vetsb.tasksalarm.tasks.domain.model.Task
import org.threeten.bp.LocalDateTime
import timber.log.Timber

class TickTickTasksRepositoryImpl(
    private val contentResolver: ContentResolver
) : TasksRepository {

    /**
     * По факту здесь suspend не нужен, потому что данные получаются из Cursor.
     * Но не исключается реализация интерфейса, где понадобится suspend.
     * Заранее перестраховался
     */
    override suspend fun getTodayTasks(): List<Task> {
        val tasks = arrayListOf<Task>()

        val today = LocalDateTime.now().toDefaultZoned().toStartDay()
        val tomorrow = today.plusDays(1).toStartDay()

        val todayStartTimeMilliseconds = today.toEpochSecond() * MILLIS_IN_SECOND
        val tomorrowStartTimeMilliseconds = tomorrow.toEpochSecond() * MILLIS_IN_SECOND

        val selectionBuilder = StringBuilder()
            .append(TickTickCursorColumn.DUEDATE.name)
            .append(" BETWEEN ")
            .append(todayStartTimeMilliseconds)
            .append(" AND ")
            .append(tomorrowStartTimeMilliseconds)

        runCatching {
            contentResolver.query(
                tasksUri,
                null,
                selectionBuilder.toString(),
                arrayOf(),
                "true",
                null
            )?.use {
                if (it.moveToFirst()) {
                    do {
                        tasks.add(it.toTask())
                    } while (it.moveToNext())
                }
            }
        }.onFailure {
            Timber.e(it)
        }

        return tasks.filter {
            if (it.dueDate == null) {
                true
            } else {
                it.dueDate.isAfter(today.toLocalDateTime()) &&
                        it.dueDate.isBefore(tomorrow.toLocalDateTime())
            }
        }.sortedBy { it.dueDate }
    }

    private fun Cursor.toTask(): Task {
        val idIndex = getColumnIndex(TickTickCursorColumn.ID.name)
        val titleIndex = getColumnIndex(TickTickCursorColumn.TITLE.name)
        val dueDateIndex = getColumnIndex(TickTickCursorColumn.DUEDATE.name)
        val priorityIndex = getColumnIndex(TickTickCursorColumn.PRIORITY.name)

        val dueDate = getLongOrNull(dueDateIndex)?.let {
            LocalDateTime.ofEpochSecond(
                it / MILLIS_IN_SECOND,
                0,
                LocalDateTime.now().toDefaultZoned().offset
            )
        }

        val priority = getIntOrNull(priorityIndex)?.let {
            when (it) {
                1 -> TaskPriority.LOW
                3 -> TaskPriority.MEDIUM
                5 -> TaskPriority.HIGH
                else -> null
            }
        }

        return Task(
            id = getLong(idIndex),
            title = getString(titleIndex),
            dueDate = dueDate,
            priority = priority
        )
    }

    companion object {
        private val tasksUri = Uri.parse("content://com.ticktick.task.data/tasks")
    }
}