package at.droidcon.vienna2016.ui.schedule.pager;

import at.droidcon.vienna2016.data.app.model.Schedule;

public interface SchedulePagerMvp {

    interface View {
        void displaySchedule(Schedule schedule);

        void displayLoadingError();
    }

    interface Presenter {
        void reloadData();
    }
}
