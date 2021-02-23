package darren.googlecloudtts;

/**
 * Author: Changemyminds.
 * Date: 2020/6/21.
 * Description:
 * Reference:
 */
public class GoogleCloudAPIConfig {
    private final String mApiKey;
    private final String mApiKeyHeader = "X-Goog-Api-Key";
    private final String mSynthesizeEndpoint = "https://texttospeech.googleapis.com/v1/text:synthesize";
    private final String mVoicesEndpoint = "https://texttospeech.googleapis.com/v1/voices";

    public GoogleCloudAPIConfig(String apiKey) {
        mApiKey = apiKey;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public String getApiKeyHeader() {
        return mApiKeyHeader;
    }

    public String getSynthesizeEndpoint() {
        return mSynthesizeEndpoint;
    }

    public String getVoicesEndpoint() {
        return mVoicesEndpoint;
    }
}
