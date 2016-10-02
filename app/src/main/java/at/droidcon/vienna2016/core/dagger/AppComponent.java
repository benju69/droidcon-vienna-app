package at.droidcon.vienna2016.core.dagger;

import javax.inject.Singleton;

import at.droidcon.vienna2016.DroidconApp;
import at.droidcon.vienna2016.core.dagger.module.ApiModule;
import at.droidcon.vienna2016.core.dagger.module.AppModule;
import at.droidcon.vienna2016.core.dagger.module.DataModule;
import at.droidcon.vienna2016.core.dagger.module.DatabaseModule;
import at.droidcon.vienna2016.core.dagger.module.UtilsModule;
import at.droidcon.vienna2016.receiver.BootReceiver;
import at.droidcon.vienna2016.receiver.reminder.ReminderReceiver;
import at.droidcon.vienna2016.ui.drawer.DrawerActivity;
import at.droidcon.vienna2016.ui.home.HomeFragment;
import at.droidcon.vienna2016.ui.schedule.day.ScheduleDayFragment;
import at.droidcon.vienna2016.ui.schedule.pager.SchedulePagerFragment;
import at.droidcon.vienna2016.ui.sessions.details.SessionDetailsActivity;
import at.droidcon.vienna2016.ui.sessions.details.SessionFeedbackDialogFragment;
import at.droidcon.vienna2016.ui.sessions.list.SessionsListActivity;
import at.droidcon.vienna2016.ui.settings.SettingsFragment;
import at.droidcon.vienna2016.ui.speakers.details.SpeakerDetailsDialogFragment;
import at.droidcon.vienna2016.ui.speakers.list.SpeakersListFragment;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, DataModule.class, DatabaseModule.class, UtilsModule.class})
public interface AppComponent {

    void inject(HomeFragment fragment);

    void inject(DrawerActivity activity);

    void inject(SchedulePagerFragment fragment);

    void inject(ScheduleDayFragment fragment);

    void inject(SessionsListActivity activity);

    void inject(SessionFeedbackDialogFragment fragment);

    void inject(SpeakersListFragment fragments);

    void inject(SessionDetailsActivity activity);

    void inject(SpeakerDetailsDialogFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(BootReceiver receiver);

    void inject(ReminderReceiver receiver);

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
                    .utilsModule(new UtilsModule())
                    .build();
        }
    }
}
