package at.droidcon.vienna2016.core.dagger;

import at.droidcon.vienna2016.DroidconApp;
import at.droidcon.vienna2016.core.dagger.module.ApiModule;
import at.droidcon.vienna2016.core.dagger.module.AppModule;
import at.droidcon.vienna2016.core.dagger.module.DataModule;
import at.droidcon.vienna2016.core.dagger.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, DataModule.class, DatabaseModule.class})
public interface AppComponent extends InternalAppGraph {

    /**
     * An initializer that creates the internal graph from an application.
     */
    final class Initializer {

        private Initializer() {
            throw new UnsupportedOperationException();
        }

        public static AppComponent init(DroidconApp app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .apiModule(new ApiModule())
                    .dataModule(new DataModule())
                    .databaseModule(new DatabaseModule())
                    .build();
        }
    }
}
