package at.droidcon.vienna2016;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;
import at.droidcon.vienna2016.core.dagger.AppComponent;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import hugo.weaving.DebugLog;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

@DebugLog
public class DroidconApp extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = BuildConfig.TWITTER_KEY;
    private static final String TWITTER_SECRET = BuildConfig.TWITTER_SECRET;


    private AppComponent component;

    public static DroidconApp get(Context context) {
        return (DroidconApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        AndroidThreeTen.init(this);
        initGraph();
        initLogger();
    }

    public AppComponent component() {
        return component;
    }

    private void initGraph() {
        component = AppComponent.Initializer.init(this);
    }

    private void initLogger() {
        Timber.plant(new Timber.DebugTree());
    }
}
