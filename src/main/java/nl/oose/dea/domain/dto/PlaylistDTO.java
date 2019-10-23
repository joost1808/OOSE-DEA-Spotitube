package nl.oose.dea.domain.dto;

import nl.oose.dea.domain.pojo.Track;

import java.util.List;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean owner;
    private List<Track> tracks;
    private int length;

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, boolean owner, List<Track> tracks, int length) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
