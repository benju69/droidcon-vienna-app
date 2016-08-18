package at.droidcon.vienna2016.data.network;

import android.content.Context;

import at.droidcon.vienna2016.BuildConfig;

public enum ApiEndpoint {

    PROD(BuildConfig.API_ENDPOINT);

    public String url;

    ApiEndpoint(String url) {
        this.url = url;
    }

    public static ApiEndpoint get(Context context) {
        return PROD;
    }
}
