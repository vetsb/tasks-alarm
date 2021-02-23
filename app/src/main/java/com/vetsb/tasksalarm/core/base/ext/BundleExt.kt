package com.vetsb.tasksalarm.core.base.ext

import android.os.Bundle

fun Bundle.toExtraString(separator: String = "\n"): String {
    return getAll().toList().joinToString(separator) {
        "${it.first} = ${it.second}"
    }
}

fun Bundle.getAll(): Map<String, Any?> {
    return keySet().map {
        it to get(it)
    }.toMap()
}