package nl.oose.dea.domain;

import java.util.Date;

public class Video extends Track {

    public Video(int id, String title, String performer, int duration, String album, int playcount, Date publicationDate, String description, boolean offlineAvailable) {
        super(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable);
    }
}
