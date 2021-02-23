package darren.googlecloudtts.model

import darren.googlecloudtts.parameter.VoiceSelectionParams
import java.util.*

/**
 * Author: Changemyminds.
 * Date: 2018/6/24.
 * Description:
 * Reference:
 */
class VoicesList {
    private var hashMap = HashMap<String, MutableList<VoiceSelectionParams>>()
    fun add(languageCode: String, params: VoiceSelectionParams) {
        var googleCloudVoices = hashMap[languageCode]
        if (googleCloudVoices == null) {
            googleCloudVoices = ArrayList()
            hashMap[languageCode] = googleCloudVoices
        }
        googleCloudVoices.add(params)
    }

    val languageCodes: List<String>
        get() {
            if (hashMap.size == 0) {
                throw NullPointerException("LanguageCodes size is zero!!")
            }

            val languages: List<String> = ArrayList(hashMap.keys)

            return languages.sorted()
        }

    fun getVoiceNames(languageCode: String): List<String> {
        if (isEmptyOrNull(languageCode)) {
            throw NullPointerException("languageCode is null")
        }

        return getGCPVoices(languageCode)
            .map { it.name }
            .sorted()
    }

    fun getGCPVoice(languageCode: String, voiceName: String): VoiceSelectionParams {
        if (isEmptyOrNull(languageCode)) {
            throw NullPointerException("LanguageCode is null or empty")
        }
        if (isEmptyOrNull(voiceName)) {
            throw NullPointerException("VoiceName is null or empty")
        }

        return getGCPVoices(languageCode)
            .firstOrNull { it.name == voiceName }
            ?: throw NullPointerException("Can't find the VoiceName $voiceName")
    }

    fun update(voicesList: VoicesList) {
        hashMap = HashMap(voicesList.hashMap)
    }

    fun clear() {
        for ((_, gcpVoices) in hashMap) {
            gcpVoices.clear()
        }
        hashMap.clear()
    }

    fun size(): Int {
        return hashMap.size
    }

    private fun getGCPVoices(languageCode: String): List<VoiceSelectionParams> {
        return hashMap[languageCode]
            ?: throw NullPointerException("Can't find the languageCode $languageCode")
    }

    private fun isEmptyOrNull(text: String?): Boolean {
        return text == null || text.length == 0
    }
}