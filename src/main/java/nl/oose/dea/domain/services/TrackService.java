package nl.oose.dea.domain.services;

import nl.oose.dea.data.TrackDAO;
import nl.oose.dea.domain.Track;
import nl.oose.dea.rest.dto.TracksDTO;

import java.util.List;

public class TrackService {
    private TrackDAO trackDAO = new TrackDAO();

    public TracksDTO getAllNotInPlaylist(int playlistid) {
        List<Track> tracks = trackDAO.findAllTracksNotInPlaylist(playlistid);
        return new TracksDTO(tracks);
    }

    public TracksDTO getAllInPlaylist(int playlistid) {
        List<Track> tracks = trackDAO.findAllTracksInPlaylist(playlistid);
        return new TracksDTO(tracks);
    }

    public void removeTrackFromPlaylist(int playlistid, int trackid) {
        trackDAO.removeTrackFromPlaylist(playlistid, trackid);
    }

    public void addTrackToPlaylist(int playlistid, Long trackid, boolean offlineAvailable) {
        trackDAO.addTrackToPlaylist(playlistid, trackid, offlineAvailable);
    }
}
