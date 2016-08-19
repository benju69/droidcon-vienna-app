package at.droidcon.vienna2016.core.dagger.module;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import javax.inject.Singleton;

import at.droidcon.vienna2016.config.ConfigProvider;
import at.droidcon.vienna2016.core.firebase.FirebaseConfigProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by helmuth on 19/08/16.
 */

@Module
public final class ConfigModule {
    @Provides @Singleton ConfigProvider provideConfigProvider() {
        return new FirebaseConfigProvider(
                FirebaseRemoteConfig.getInstance());
    }
}
