package nl.oose.dea.domain.services;

import nl.oose.dea.data.PlaylistDAO;
import nl.oose.dea.domain.Playlist;
import nl.oose.dea.domain.exceptions.IdAlreadyInUseException;
import nl.oose.dea.domain.exceptions.ItemNotAvailableException;
import nl.oose.dea.rest.dto.PlaylistsDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlaylistService {
    private PlaylistDAO playlistDAO = new PlaylistDAO();
    private List<Playlist> playlists;

    public PlaylistsDTO getAll(String token) {
        playlists = playlistDAO.getAllPlaylists(token);
        return new PlaylistsDTO(playlists, 0);
    }

    public void deletePlaylist(int id) {
        Optional<Playlist> itemForName = playlists.stream().filter(playlist -> playlist.getId() == id).findFirst();
        List<Playlist> filteredItems = playlists.stream().filter(playlist -> playlist.getId() != id).collect(Collectors.toList());
        if (filteredItems.size() == playlists.size()) {
            throw new ItemNotAvailableException();
        }
        playlists = filteredItems;
    }

    public void addPlaylist(Playlist playlist) {
        if (playlists.stream().anyMatch(pl -> pl.getId() == playlist.getId())) {
            throw new IdAlreadyInUseException();
        }

        playlists.add(playlist);
    }

    public void editPlaylist(int id) {

    }
}
