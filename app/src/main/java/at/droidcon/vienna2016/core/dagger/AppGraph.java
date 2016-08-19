package at.droidcon.vienna2016.core.dagger;

import at.droidcon.vienna2016.receiver.BootReceiver;
import at.droidcon.vienna2016.ui.drawer.DrawerActivity;
import at.droidcon.vienna2016.ui.drawer.DrawerPresenter;
import at.droidcon.vienna2016.ui.schedule.day.ScheduleDayFragment;
import at.droidcon.vienna2016.ui.schedule.pager.SchedulePagerFragment;
import at.droidcon.vienna2016.ui.sessions.details.SessionDetailsActivity;
import at.droidcon.vienna2016.ui.sessions.list.SessionsListActivity;
import at.droidcon.vienna2016.ui.settings.SettingsFragment;
import at.droidcon.vienna2016.ui.speakers.details.SpeakerDetailsDialogFragment;
import at.droidcon.vienna2016.ui.speakers.list.SpeakersListFragment;

/**
 * A common interface implemented by both the internal and production flavored components.
 */
public interface AppGraph {

    void inject(DrawerActivity activity);

    void inject(DrawerPresenter presenter);

    void inject(SchedulePagerFragment fragment);

    void inject(ScheduleDayFragment fragment);

    void inject(SessionsListActivity activity);

    void inject(SpeakersListFragment fragments);

    void inject(SessionDetailsActivity activity);

    void inject(SpeakerDetailsDialogFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(BootReceiver receiver);
}
