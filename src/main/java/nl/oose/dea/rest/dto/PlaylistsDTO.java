package nl.oose.dea.rest.dto;

import nl.oose.dea.domain.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsDTO {
    private List<Playlist> playlists = new ArrayList<>();
    private int length;

    public PlaylistsDTO() {

    }

    public PlaylistsDTO(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
