package com.vetsb.tasksalarm.speaker.data.google

import com.vetsb.tasksalarm.speaker.domain.SpeakerRepository
import darren.googlecloudtts.GoogleCloudTTS

class GoogleCloudSpeakerRepositoryImpl(
    private val googleCloudTTS: GoogleCloudTTS
) : SpeakerRepository {

    override suspend fun speak(text: String) {
        googleCloudTTS.start(text)
    }

    override fun stop() {
        googleCloudTTS.stop()
    }
}