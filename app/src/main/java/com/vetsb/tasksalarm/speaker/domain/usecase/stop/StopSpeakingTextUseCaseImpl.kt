package com.vetsb.tasksalarm.speaker.domain.usecase.stop

import com.vetsb.tasksalarm.speaker.domain.SpeakerRepository

class StopSpeakingTextUseCaseImpl(
    private val speakerRepository: SpeakerRepository
) : StopSpeakingTextUseCase {

    override fun invoke() {
        speakerRepository.stop()
    }
}