package nl.oose.dea.rest;

import nl.oose.dea.domain.dto.PlaylistsDTO;

public interface iPlaylistService {
    PlaylistsDTO getAll(String token);
    void deletePlaylist(int id, String token);
    void addPlaylist(String playlistName, String token);
    void editPlaylist(String newName, int id);
}
