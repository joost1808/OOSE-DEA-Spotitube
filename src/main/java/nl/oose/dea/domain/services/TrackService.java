package nl.oose.dea.domain.services;

import nl.oose.dea.data.TrackDAO;
import nl.oose.dea.domain.Track;
import nl.oose.dea.rest.dto.TracksDTO;

import java.util.List;

public class TrackService {
    private TrackDAO trackDAO = new TrackDAO();

    public TracksDTO getAllNotInPlaylist(int id) {
        List<Track> tracks = trackDAO.findAllTracksNotInPlaylist(id);
        return new TracksDTO(tracks);
    }

    public TracksDTO getAllInPlaylist(int id) {
        List<Track> tracks = trackDAO.findAllTracksInPlaylist(id);
        return new TracksDTO(tracks);
    }
}
