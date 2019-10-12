package nl.oose.dea.domain;

import java.util.Date;

public class Song extends Track {

    public Song(Long id, String title, String performer, int duration, String album, int playcount, String publicationDate, String description, boolean offlineAvailable) {
        super(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable);
    }
}
