package nl.oose.dea.domain.services;

import nl.oose.dea.domain.iPlaylistDAO;
import nl.oose.dea.domain.pojo.Playlist;
import nl.oose.dea.domain.dto.PlaylistsDTO;
import nl.oose.dea.rest.iPlaylistService;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements iPlaylistService {
    private iPlaylistDAO playlistDAO;

    @Inject
    public void setPlaylistDAO(iPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public PlaylistsDTO getAll(String token) {
        List<Playlist> playlists = playlistDAO.getAllPlaylists(token);
        return new PlaylistsDTO(playlists, calcTotalPlaylistsLength(playlists));
    }

    private int calcTotalPlaylistsLength(List<Playlist> playlists) {
        int totalLength = 0;
        for (Playlist playlist : playlists) {
            totalLength += playlist.getLength();
        }
        return totalLength;
    }

    public void deletePlaylist(int id, String token) {
        playlistDAO.deletePlaylist(id, token);
    }

    public void addPlaylist(String playlistName, String token) {
        playlistDAO.addPlaylist(playlistName, token);
    }

    public void editPlaylist(String newName, int id) {
        playlistDAO.editPlaylist(newName, id);
    }
}
