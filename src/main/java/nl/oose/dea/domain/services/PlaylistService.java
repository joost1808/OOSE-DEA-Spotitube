package nl.oose.dea.domain.services;

import nl.oose.dea.data.PlaylistDAO;
import nl.oose.dea.domain.Playlist;
import nl.oose.dea.domain.exceptions.IdAlreadyInUseException;
import nl.oose.dea.rest.dto.PlaylistsDTO;

import java.util.List;

public class PlaylistService {
    private PlaylistDAO playlistDAO = new PlaylistDAO();

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

    public void addPlaylist(int playlistid, String token) {
        playlistDAO.addPlaylist(playlistid, token);
    }

    public void editPlaylist(String newName, int id) {
        playlistDAO.editPlaylist(newName, id);
    }
}
