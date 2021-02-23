package darren.googlecloudtts

import android.media.MediaPlayer
import darren.googlecloudtts.api.SynthesizeApi
import darren.googlecloudtts.exception.ApiException
import darren.googlecloudtts.parameter.AudioConfig
import darren.googlecloudtts.parameter.SynthesisInput
import darren.googlecloudtts.parameter.VoiceSelectionParams
import darren.googlecloudtts.request.SynthesizeRequest
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Author: Changemyminds.
 * Date: 2018/6/24.
 * Description:
 * Reference:
 */
class GoogleCloudTTS(
    private val api: SynthesizeApi
) : AutoCloseable {

    var voiceSelectionParams: VoiceSelectionParams? = null
    var audioConfig: AudioConfig? = null

    private var mediaPlayer: MediaPlayer? = null

    suspend fun start(text: String) {
        if (voiceSelectionParams == null) {
            throw NullPointerException("You forget to setVoiceSelectionParams()")
        }

        if (audioConfig == null) {
            throw NullPointerException("You forget to setAudioConfig()")
        }

        val request = SynthesizeRequest(
            SynthesisInput(null, text),
            voiceSelectionParams,
            audioConfig
        )

        runCatching {
            val response = api.get(request)
            playAudio(response.audioContent)

            suspendCoroutine<Unit> { cont ->
                mediaPlayer?.setOnSeekCompleteListener {
                    if (it.currentPosition == it.duration) {
                        mediaPlayer?.setOnSeekCompleteListener(null)
                        cont.resume(Unit)
                    }
                }
            }
        }.onFailure {
            throw ApiException(it)
        }
    }

    fun stop() {
        mediaPlayer?.run {
            if (isPlaying) {
                seekTo(duration)
            }
        }
    }

    @Throws(IOException::class)
    private fun playAudio(base64EncodedString: String) {
        stop()

        val url = "data:audio/mp3;base64,$base64EncodedString"

        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            prepare()
        }

        mediaPlayer?.start()
    }

    override fun close() {
        stop()

        mediaPlayer?.release()
        mediaPlayer = null
    }
}