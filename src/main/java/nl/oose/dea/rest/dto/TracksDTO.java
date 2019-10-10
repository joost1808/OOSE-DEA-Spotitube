package nl.oose.dea.rest.dto;

import nl.oose.dea.domain.Track;

import java.util.ArrayList;
import java.util.List;

public class TracksDTO {
    private List<Track> tracks = new ArrayList<>();

    public TracksDTO() {
    }

    public TracksDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
