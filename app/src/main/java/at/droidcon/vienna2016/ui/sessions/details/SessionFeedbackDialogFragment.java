package at.droidcon.vienna2016.ui.sessions.details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import javax.inject.Inject;

import at.droidcon.vienna2016.DroidconApp;
import at.droidcon.vienna2016.R;
import at.droidcon.vienna2016.data.app.model.Session;
import at.droidcon.vienna2016.ui.speakers.details.SpeakerDetailsDialogFragment;
import at.droidcon.vienna2016.utils.Analytics;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by helmuth on 21/08/16.
 */

public class SessionFeedbackDialogFragment extends AppCompatDialogFragment {

    @Inject Analytics analytics;

    @BindView(R.id.session_feedback_title) TextView title;
    @BindView(R.id.session_feedback_talk) RatingBar talk;
    @BindView(R.id.session_feedback_speaker) RatingBar speaker;
    @BindView(R.id.session_feedback_content) RatingBar content;

    private Unbinder unbinder;

    private static final String EXTRA_SESSION = "session";

    private Session session;

    public static void show(@NonNull Session session, @NonNull FragmentManager fm) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_SESSION, session);
        SessionFeedbackDialogFragment fragment = new SessionFeedbackDialogFragment();
        fragment.setArguments(args);
        fragment.show(fm, SessionFeedbackDialogFragment.class.getSimpleName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DroidconApp.get(getContext()).component().inject(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(getContext()).inflate(R.layout.session_feedback, null);
        unbinder = ButterKnife.bind(this, view);
        bindSession(getArguments().getParcelable(EXTRA_SESSION));
        return new AlertDialog.Builder(getContext())
                .setView(view)
                .setPositiveButton(R.string.session_feedback_submit, (dialog, which) -> dismiss())
                .setNegativeButton(R.string.session_feedback_cancel, (dialog, which) -> dismiss())
                .create();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    private void bindSession(Session session) {
        this.session = session;
        // analytics.logViewSpeaker(speaker.getId(), speaker.getName());
        title.setText(session.getTitle());
    }
}
