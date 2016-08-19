package at.droidcon.vienna2016.ui.settings;

import android.content.Context;

import at.droidcon.vienna2016.receiver.BootReceiver;
import at.droidcon.vienna2016.receiver.reminder.SessionsReminder;
import at.droidcon.vienna2016.ui.BasePresenter;
import at.droidcon.vienna2016.utils.Analytics;
import at.droidcon.vienna2016.utils.App;

public class SettingsPresenter extends BasePresenter<SettingsMvp.View> implements SettingsMvp.Presenter {

    private final Context context;
    private final SessionsReminder sessionsReminder;
    private final Analytics analytics;

    public SettingsPresenter(Context context, SettingsMvp.View view, SessionsReminder sessionsReminder, Analytics analytics) {
        super(view);
        this.context = context;
        this.sessionsReminder = sessionsReminder;
        this.analytics = analytics;
    }

    @Override
    public void onCreate() {
        view.setAppVersion(App.getVersion());
    }

    @Override
    public boolean onNotifySessionsChange(boolean checked) {
        view.setNotifySessionsCheckbox(checked);

        if (checked) {
            sessionsReminder.enableSessionReminder();
            BootReceiver.enable(context);
        } else {
            sessionsReminder.disableSessionReminder();
            BootReceiver.disable(context);
        }
        analytics.setNotificationFlag(checked);
        return true;
    }
}
