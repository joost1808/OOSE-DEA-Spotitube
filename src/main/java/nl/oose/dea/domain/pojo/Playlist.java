package nl.oose.dea.domain.pojo;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private int id;
    private String name;
    private boolean owner;
    private List<Track> tracks;
    private int length;

    public Playlist(int id, String name, boolean owner, List<Track> tracks, int length) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return id == playlist.id &&
                owner == playlist.owner &&
                length == playlist.length &&
                Objects.equals(name, playlist.name) &&
                Objects.equals(tracks, playlist.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, tracks, length);
    }
}
