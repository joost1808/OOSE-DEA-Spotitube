package nl.oose.dea.rest;

import nl.oose.dea.domain.pojo.Playlist;
import nl.oose.dea.domain.services.PlaylistService;
import nl.oose.dea.domain.services.TrackService;
import nl.oose.dea.rest.dto.PlaylistDTO;
import nl.oose.dea.rest.dto.PlaylistsDTO;
import nl.oose.dea.rest.dto.TrackDTO;
import nl.oose.dea.rest.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistResourceTest {
private PlaylistResource sut;
private PlaylistService mockedPlaylistService;
private TrackService mockedTrackService;

    @BeforeEach
    void setUp() {
        sut = new PlaylistResource();
        this.mockedPlaylistService = Mockito.mock(PlaylistService.class);
        this.mockedTrackService = Mockito.mock(TrackService.class);
        this.sut.setPlaylistService(mockedPlaylistService);
        this.sut.setTrackService(mockedTrackService);
    }

    @Test
    void verifyGetPlaylists() {
        UUID token = UUID.randomUUID();
        sut.getPlaylists(String.valueOf(token));
        Mockito.verify(mockedPlaylistService).getAll(String.valueOf(token));
    }

    @Test
    void getPlaylistsAsEntity() {
        UUID token = UUID.randomUUID();
        var itemsToReturn = new PlaylistsDTO();
        Mockito.when(mockedPlaylistService.getAll(String.valueOf(token))).thenReturn(itemsToReturn);
        var input = sut.getPlaylists(String.valueOf(token));
        assertEquals(input.getEntity(), itemsToReturn);
    }

    @Test
    void getPlaylistsReturnsBadRequest() {
        int statusCode = sut.getPlaylists(null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void verifyDeletePlaylist() {
        UUID token = UUID.randomUUID();
        sut.deletePlaylist(1, String.valueOf(token));
        Mockito.verify(mockedPlaylistService).deletePlaylist(1, String.valueOf(token));
    }

    @Test
    void verifyAddPlaylist() {
        UUID token = UUID.randomUUID();
        PlaylistDTO playlist = new PlaylistDTO();
        sut.addPlaylist(playlist, String.valueOf(token));
        Mockito.verify(mockedPlaylistService).addPlaylist(playlist.getName(), String.valueOf(token));
    }

    @Test
    void addPlaylistReturnsBadRequest() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        int statusCode = sut.addPlaylist(playlistDTO, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void verifyEditPlaylist() {
        UUID token = UUID.randomUUID();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        sut.editPlaylist(playlistDTO, 1, String.valueOf(token));
        Mockito.verify(mockedPlaylistService).editPlaylist(playlistDTO.getName(), 1);
    }

    @Test
    void editPlaylistReturnsBadRequest() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        int statusCode = sut.editPlaylist(playlistDTO, 1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void verifyGetTracksInPlaylist() {
        UUID token = UUID.randomUUID();
        sut.getTracksInPlaylist(1, String.valueOf(token));
        Mockito.verify(mockedTrackService).getAllInPlaylist(1);
    }

    @Test
    void getTracksInPlaylistAsEntity() {
        UUID token = UUID.randomUUID();
        var itemsToReturn = new TracksDTO();
        Mockito.when(mockedTrackService.getAllInPlaylist(1)).thenReturn(itemsToReturn);
        var input = sut.getTracksInPlaylist(1, String.valueOf(token));
        assertEquals(input.getEntity(), itemsToReturn);
    }

    @Test
    void getTracksInPlaylistReturnsBadRequest() {
        int statusCode = sut.getTracksInPlaylist(1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void verifyRemoveTrackFromPlaylist() {
        UUID token = UUID.randomUUID();
        sut.removeTrackFromPlaylist(1, 1, String.valueOf(token));
        Mockito.verify(mockedTrackService).removeTrackFromPlaylist(1, 1);
    }

    @Test
    void removeTrackFromPlaylistReturnsBadRequest() {
        int statusCode = sut.removeTrackFromPlaylist(1, 1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void verifyAddTrackToPlaylist() {
        UUID token = UUID.randomUUID();
        TrackDTO trackDTO = new TrackDTO();
        sut.addTrackToPlaylist(trackDTO, 1, String.valueOf(token));
        Mockito.verify(mockedTrackService).addTrackToPlaylist(1, trackDTO.getId(), trackDTO.isOfflineAvailable());
    }

    @Test
    void addTrackToPlaylistReturnsBadRequest() {
        TrackDTO trackDTO = new TrackDTO();
        int statusCode = sut.addTrackToPlaylist(trackDTO, 1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }
}