package at.droidcon.vienna2016.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import at.droidcon.vienna2016.DroidconApp;
import at.droidcon.vienna2016.R;
import at.droidcon.vienna2016.data.app.DataProvider;
import at.droidcon.vienna2016.data.app.SelectedSessionsMemory;
import at.droidcon.vienna2016.data.app.model.Session;
import at.droidcon.vienna2016.ui.BaseFragment;
import at.droidcon.vienna2016.ui.sessions.details.SessionDetailsActivityIntentBuilder;
import at.droidcon.vienna2016.ui.sessions.list.SessionsListAdapter;
import at.droidcon.vienna2016.ui.sessions.list.SessionsListMvp;
import at.droidcon.vienna2016.utils.Analytics;
import at.droidcon.vienna2016.utils.Intents;
import butterknife.BindView;

/**
 * Created by helmuth on 26/08/16.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeMvp.View, SessionsListMvp.SessionDetailsHandler {

    @BindView(R.id.home_news_card) CardView newsCard;
    @BindView(R.id.home_news_title) TextView newsTitle;
    @BindView(R.id.home_news_text) TextView newsText;
    @BindView(R.id.home_current_title) TextView currentTitle;
    @BindView(R.id.home_current_text) TextView currentText;
    @BindView(R.id.home_current_recyclerview) RecyclerView currentRecyclerView;
    @BindView(R.id.home_content) View content;
    @BindView(R.id.home_loading) View loading;
    @BindView(R.id.home_sponsor_shpock) View spock;
    @BindView(R.id.home_sponsor_runtastic) View runtastic;
    @BindView(R.id.home_sponsor_creativeworkline) View creativworkline;
    @BindView(R.id.home_sponsor_c4c_engineering) View c4c_engineering;
    @Inject Analytics analytics;
    @Inject DatabaseReference dbRef;
    @Inject DataProvider dataProvider;
    @Inject Picasso picasso;
    @Inject SelectedSessionsMemory selectedSessionsMemory;

    @Override
    public void hideAnnouncement() {
        newsCard.setVisibility(View.GONE);
    }

    @Override
    public void setComingNext(@StringRes int title, @StringRes int text, @Nullable List<Session> sessions) {
        //
        currentTitle.setText(title);
        currentText.setText(text);
        if (sessions != null) {
            SessionsListAdapter adapter = new SessionsListAdapter(sessions, picasso, selectedSessionsMemory, this, true);
            currentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            currentRecyclerView.setHasFixedSize(true);
            currentRecyclerView.setAdapter(adapter);
            currentRecyclerView.setVisibility(View.VISIBLE);
        }
        else {
            currentRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        content.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        loading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void openLink(String url) {
        if (url != null) {
            Intents.startExternalUrl(getActivity(), url);
        }
    }

    @Override
    protected HomePresenter newPresenter() {
        return new HomePresenter(this, dbRef, analytics, dataProvider);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DroidconApp.get(getContext()).component().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        View.OnClickListener clickListener = v -> openLink(v.getTag() != null ? v.getTag().toString() : null) ;
        spock.setOnClickListener(clickListener);
        runtastic.setOnClickListener(clickListener);
        creativworkline.setOnClickListener(clickListener);
        c4c_engineering.setOnClickListener(clickListener);
    }

    @Override
    public void updateAnnouncement(String title, String text) {
        newsCard.setVisibility(View.VISIBLE);
        newsTitle.setText(title);
        newsText.setText(text);
    }

    @Override
    public void startSessionDetails(Session session) {
        startActivity(new SessionDetailsActivityIntentBuilder(session).build(getContext()));
    }
}
