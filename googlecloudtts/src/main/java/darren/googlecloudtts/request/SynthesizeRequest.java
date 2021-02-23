package darren.googlecloudtts.request;

import darren.googlecloudtts.parameter.AudioConfig;
import darren.googlecloudtts.parameter.SynthesisInput;
import darren.googlecloudtts.parameter.VoiceSelectionParams;

/**
 * Author: Changemyminds.
 * Date: 2020/12/17.
 * Description:
 * Reference:
 */
public class SynthesizeRequest {
    private SynthesisInput input;
    private VoiceSelectionParams voice;
    private AudioConfig audioConfig;

    public SynthesizeRequest(SynthesisInput input, VoiceSelectionParams GCPVoice, AudioConfig audioConfig) {
        this.input = input;
        this.voice = GCPVoice;
        this.audioConfig = audioConfig;
    }

    public SynthesisInput getInput() {
        return input;
    }

    public void setInput(SynthesisInput input) {
        this.input = input;
    }

    public VoiceSelectionParams getVoice() {
        return voice;
    }

    public void setVoice(VoiceSelectionParams voice) {
        this.voice = voice;
    }

    public AudioConfig getAudioConfig() {
        return audioConfig;
    }

    public void setAudioConfig(AudioConfig audioConfig) {
        this.audioConfig = audioConfig;
    }
}
