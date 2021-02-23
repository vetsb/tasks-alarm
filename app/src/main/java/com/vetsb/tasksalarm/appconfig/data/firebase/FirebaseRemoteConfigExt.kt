package com.vetsb.tasksalarm.appconfig.data.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vetsb.tasksalarm.appconfig.data.firebase.constant.FirebaseRemoteConfigKeys
import com.vetsb.tasksalarm.appconfig.domain.model.AppConfig

fun Map<String, FirebaseRemoteConfigValue>.toDomain(gson: Gson): AppConfig {
    return AppConfig(
        alarmApplications = getAlarmApplications(gson)
    )
}

private fun Map<String, FirebaseRemoteConfigValue>.getAlarmApplications(gson: Gson): List<String> {
    val json = get(FirebaseRemoteConfigKeys.ALARM_APPLICATIONS)?.asString()
    val type = TypeToken.getParameterized(
        List::class.java,
        String::class.java
    ).type

    return runCatching {
        gson.fromJson<List<String>>(json, type) ?: listOf()
    }.getOrDefault(listOf())
}