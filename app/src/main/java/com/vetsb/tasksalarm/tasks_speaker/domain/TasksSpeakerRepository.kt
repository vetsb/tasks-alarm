package com.vetsb.tasksalarm.tasks_speaker.domain

interface TasksSpeakerRepository {
    fun showNotification()
    fun hideNotification()
}