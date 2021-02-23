package com.vetsb.tasksalarm.speaker.domain.usecase.start

interface StartSpeakingTextUseCase {
    suspend operator fun invoke(text: String)
}