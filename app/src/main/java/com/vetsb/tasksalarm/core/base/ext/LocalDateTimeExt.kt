package com.vetsb.tasksalarm.core.base.ext

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

fun LocalDateTime.toDefaultZoned(): ZonedDateTime {
    return atZone(ZoneId.systemDefault())
}