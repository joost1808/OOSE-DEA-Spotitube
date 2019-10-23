package nl.oose.dea.domain.services;

import nl.oose.dea.domain.iTrackDAO;
import nl.oose.dea.domain.pojo.Track;
import nl.oose.dea.domain.dto.TracksDTO;
import nl.oose.dea.rest.iTrackService;

import javax.inject.Inject;
import java.util.List;

public class TrackService implements iTrackService {
    private iTrackDAO trackDAO;

    @Inject
    public void setTrackDAO(iTrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

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
