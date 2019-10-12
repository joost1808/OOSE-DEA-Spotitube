package nl.oose.dea.domain.services;

import nl.oose.dea.data.PlaylistDAO;
import nl.oose.dea.domain.Playlist;
import nl.oose.dea.domain.exceptions.IdAlreadyInUseException;
import nl.oose.dea.rest.dto.PlaylistsDTO;

import java.util.List;

public class PlaylistService {
    private PlaylistDAO playlistDAO = new PlaylistDAO();
    private List<Playlist> playlists;

    public PlaylistsDTO getAll(String token) {
        playlists = playlistDAO.getAllPlaylists(token);
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

    public void addPlaylist(Playlist playlist) {
        if (playlists.stream().anyMatch(pl -> pl.getId() == playlist.getId())) {
            throw new IdAlreadyInUseException();
        }

        playlists.add(playlist);
    }

    public void editPlaylist(String newName, int id) {
        playlistDAO.editPlaylist(newName, id);
    }
}
