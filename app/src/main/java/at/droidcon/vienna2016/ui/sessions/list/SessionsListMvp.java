package at.droidcon.vienna2016.ui.sessions.list;

import at.droidcon.vienna2016.data.app.model.Session;

import java.util.List;

public interface SessionsListMvp {

    interface View {
        void initToobar(String title);

        void initSessionsList(List<Session> sessions);

        void startSessionDetails(Session session);
    }
}
