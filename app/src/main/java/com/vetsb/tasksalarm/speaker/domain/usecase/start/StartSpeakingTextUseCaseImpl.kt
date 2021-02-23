package com.vetsb.tasksalarm.speaker.domain.usecase.start

import com.vetsb.tasksalarm.speaker.domain.SpeakerRepository

class StartSpeakingTextUseCaseImpl(
    private val speakerRepository: SpeakerRepository
) : StartSpeakingTextUseCase {

    override suspend fun invoke(text: String) {
        speakerRepository.speak(text)
    }
}