package nl.oose.dea.domain;

import nl.oose.dea.domain.pojo.Track;

import java.util.List;

public interface iTrackDAO {
    List<Track> findAllTracksNotInPlaylist(int id);
    List<Track> findAllTracksInPlaylist(int id);
    void removeTrackFromPlaylist(int playlistid, int trackid);
    void addTrackToPlaylist(int playlistid, Long trackid, boolean offlineAvailable);
}
