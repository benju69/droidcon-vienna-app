package at.droidcon.vienna2016.data.app.model;

import lombok.Value;

/**
 * Created by helmuth on 27/08/16.
 *
 * This class models the entry in Firebase for news announcements
 */

@Value
public class NewsEntry {
    String title = null;
    String text = null;
    String url = null;

    public NewsEntry() { }
}
