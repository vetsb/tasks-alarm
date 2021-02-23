package com.vetsb.tasksalarm.tasks_speaker.domain.scenario

interface SpeakTodayTasksScenario {
    suspend operator fun invoke()
}