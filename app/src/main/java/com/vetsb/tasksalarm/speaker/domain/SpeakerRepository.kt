package com.vetsb.tasksalarm.speaker.domain

interface SpeakerRepository {
    suspend fun speak(text: String)
    fun stop()
}