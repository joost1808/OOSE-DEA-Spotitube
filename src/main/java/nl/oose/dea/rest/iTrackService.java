package nl.oose.dea.rest;

import nl.oose.dea.rest.dto.TracksDTO;

public interface iTrackService {
    TracksDTO getAllNotInPlaylist(int playlistid);
    TracksDTO getAllInPlaylist(int playlistid);
    void removeTrackFromPlaylist(int playlistid, int trackid);
    void addTrackToPlaylist(int playlistid, Long trackid, boolean offlineAvailable);
}
