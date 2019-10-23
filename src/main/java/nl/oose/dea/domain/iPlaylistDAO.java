package nl.oose.dea.domain;

import nl.oose.dea.domain.pojo.Playlist;

import java.util.List;

public interface iPlaylistDAO {
    List<Playlist> getAllPlaylists(String token);
    void addPlaylist(String playlistName, String token);
    void deletePlaylist(int playlistid, String token);
    void editPlaylist(String name, int playlistid);
}
