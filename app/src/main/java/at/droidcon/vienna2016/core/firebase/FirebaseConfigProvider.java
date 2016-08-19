package at.droidcon.vienna2016.core.firebase;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import at.droidcon.vienna2016.R;
import at.droidcon.vienna2016.config.ConfigProvider;

/**
 * Created by helmuth on 19/08/16.
 *
 * This class implements a ConfigProvider using Firebase Remote Config.
 */

public class FirebaseConfigProvider implements ConfigProvider {
    private FirebaseRemoteConfig cfg;

    public FirebaseConfigProvider(FirebaseRemoteConfig cfg) {
        this.cfg = cfg;
        // load default values
        cfg.setDefaults(R.xml.defaults);
        // start fetching new values
        cfg.fetch();
    }

    @Override
    public void refresh() {
        // activate newly fetched values - if existing
        cfg.activateFetched();
        // and fetch new values in the background
        cfg.fetch();
    }

    @Override
    public boolean getBoolean(String key) {
        return cfg.getBoolean(key);
    }

    @Override
    public long getLong(String key) {
        return cfg.getLong(key);
    }

    @Override
    public double getDouble(String key) {
        return cfg.getDouble(key);
    }

    @Override
    public String getString(String key) {
        return cfg.getString(key);
    }
}
