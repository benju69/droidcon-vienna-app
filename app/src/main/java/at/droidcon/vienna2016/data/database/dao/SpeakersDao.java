package at.droidcon.vienna2016.data.database.dao;

import at.droidcon.vienna2016.data.app.AppMapper;
import at.droidcon.vienna2016.data.database.DbMapper;
import at.droidcon.vienna2016.data.database.model.Speaker;
import at.droidcon.vienna2016.utils.Preconditions;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class SpeakersDao {

    private final BriteDatabase database;
    private final DbMapper dbMapper;
    private final AppMapper appMapper;

    @Inject
    public SpeakersDao(BriteDatabase database, DbMapper dbMapper, AppMapper appMapper) {
        this.database = database;
        this.dbMapper = dbMapper;
        this.appMapper = appMapper;
    }

    public Observable<Map<Integer, at.droidcon.vienna2016.data.app.model.Speaker>> getSpeakersMap() {
        return getSpeakers().map(appMapper::speakersToMap);
    }

    public Observable<List<at.droidcon.vienna2016.data.app.model.Speaker>> getSpeakers() {
        return database.createQuery(Speaker.TABLE, "SELECT * FROM " + Speaker.TABLE)
                .mapToList(Speaker.MAPPER)
                .first()
                .map(dbMapper::toAppSpeakers);
    }

    public void saveSpeakers(List<at.droidcon.vienna2016.data.app.model.Speaker> toSave) {
        Preconditions.checkNotOnMainThread();

        BriteDatabase.Transaction transaction = database.newTransaction();
        try {
            database.delete(Speaker.TABLE, null);
            for (at.droidcon.vienna2016.data.app.model.Speaker speaker : toSave) {
                database.insert(Speaker.TABLE, Speaker.createContentValues(dbMapper.fromAppSpeaker(speaker)));
            }
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }
}
