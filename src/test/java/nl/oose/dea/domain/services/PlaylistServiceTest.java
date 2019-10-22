package nl.oose.dea.domain.services;

import nl.oose.dea.data.PlaylistDAO;
import nl.oose.dea.domain.pojo.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistServiceTest {
    private PlaylistService sut;
    private PlaylistDAO mockedPlaylistDAO;

    @BeforeEach
    void setUp() {
        sut = new PlaylistService();
        this.mockedPlaylistDAO = Mockito.mock(PlaylistDAO.class);
        this.sut.setPlaylistDAO(mockedPlaylistDAO);
    }

    @Test
    void verifyGetAll() {
        UUID token = UUID.randomUUID();
        sut.getAll(String.valueOf(token));
        Mockito.verify(mockedPlaylistDAO).getAllPlaylists(String.valueOf(token));
    }

    @Test
    void getAllAsEntity() {
        UUID token = UUID.randomUUID();
        List<Playlist> itemsToReturn = new ArrayList<>();
        Mockito.when(mockedPlaylistDAO.getAllPlaylists(String.valueOf(token))).thenReturn(itemsToReturn);
        var input = sut.getAll(String.valueOf(token));
        assertEquals(input.getPlaylists(), itemsToReturn);
    }

    @Test
    void verifyDeletePlaylist() {
        UUID token = UUID.randomUUID();
        sut.deletePlaylist(1, String.valueOf(token));
        Mockito.verify(mockedPlaylistDAO).deletePlaylist(1, String.valueOf(token));
    }

    @Test
    void verifyAddPlaylist() {
        UUID token = UUID.randomUUID();
        sut.addPlaylist("test", String.valueOf(token));
        Mockito.verify(mockedPlaylistDAO).addPlaylist("test", String.valueOf(token));
    }

    @Test
    void verifyEditPlaylist() {
        sut.editPlaylist("test", 1);
        Mockito.verify(mockedPlaylistDAO).editPlaylist("test", 1);
    }
}