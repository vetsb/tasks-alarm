package com.vetsb.tasksalarm.core.base.ext

import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

fun ZonedDateTime.toStartDay(): ZonedDateTime {
    return ZonedDateTime.of(year, month.value, dayOfMonth, 0, 0, 0, 0, ZoneOffset.systemDefault())
}