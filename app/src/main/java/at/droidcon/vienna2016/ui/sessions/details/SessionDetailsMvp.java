package at.droidcon.vienna2016.ui.sessions.details;

import android.support.annotation.StringRes;

import at.droidcon.vienna2016.data.app.model.Session;

public interface SessionDetailsMvp {

    interface View {
        void bindSessionDetails(Session session);

        void updateFabButton(boolean isSelected, boolean animate);

        void showSnackbarMessage(@StringRes int resId);

        void enableFeedback(boolean show);
    }

    interface Presenter {
        void onFloatingActionButtonClicked();
    }
}
