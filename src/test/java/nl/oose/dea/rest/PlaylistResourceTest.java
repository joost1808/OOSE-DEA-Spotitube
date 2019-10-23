package nl.oose.dea.rest;

import nl.oose.dea.domain.dto.PlaylistDTO;
import nl.oose.dea.domain.dto.PlaylistsDTO;
import nl.oose.dea.domain.dto.TrackDTO;
import nl.oose.dea.domain.dto.TracksDTO;
import nl.oose.dea.rest.resources.PlaylistResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistResourceTest {
private PlaylistResource sut;
private iPlaylistService mockedPlaylistService;
private iTrackService mockedTrackService;
private iUserService mockedUserService;

    @BeforeEach
    void setUp() {
        sut = new PlaylistResource();
        this.mockedPlaylistService = Mockito.mock(iPlaylistService.class);
        this.mockedTrackService = Mockito.mock(iTrackService.class);
        this.mockedUserService = Mockito.mock(iUserService.class);
        this.sut.setPlaylistService(mockedPlaylistService);
        this.sut.setTrackService(mockedTrackService);
        this.sut.setUserService(mockedUserService);
    }

    @Test
    void verifyGetPlaylists() {
        UUID token = UUID.randomUUID();
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
        sut.getPlaylists(String.valueOf(token));
        Mockito.verify(mockedPlaylistService).getAll(String.valueOf(token));
    }

    @Test
    void getPlaylistsAsEntity() {
        UUID token = UUID.randomUUID();
        var itemsToReturn = new PlaylistsDTO();
        Mockito.when(mockedPlaylistService.getAll(String.valueOf(token))).thenReturn(itemsToReturn);
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
        var input = sut.getPlaylists(String.valueOf(token));
        assertEquals(input.getEntity(), itemsToReturn);
    }

    @Test
    void getPlaylistsReturnsBadRequest() {
        int statusCode = sut.getPlaylists(null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void getPlaylistsReturnsForbidden() {
        UUID token = UUID.randomUUID();
        int statusCode = sut.getPlaylists(String.valueOf(token)).getStatus();
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
    }

    @Test
    void verifyDeletePlaylist() {
        UUID token = UUID.randomUUID();
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
        sut.deletePlaylist(1, String.valueOf(token));
        Mockito.verify(mockedPlaylistService).deletePlaylist(1, String.valueOf(token));
    }

    @Test
    void deletePlaylistsReturnsBadRequest() {
        int statusCode = sut.deletePlaylist(1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void deletePlaylistsReturnsForbidden() {
        UUID token = UUID.randomUUID();
        int statusCode = sut.deletePlaylist(1, String.valueOf(token)).getStatus();
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
    }

    @Test
    void verifyAddPlaylist() {
        UUID token = UUID.randomUUID();
        PlaylistDTO playlist = new PlaylistDTO();
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
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
    void addPlaylistReturnsForbidden() {
        UUID token = UUID.randomUUID();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        int statusCode = sut.addPlaylist(playlistDTO, String.valueOf(token)).getStatus();
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
    }

    @Test
    void verifyEditPlaylist() {
        UUID token = UUID.randomUUID();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
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
    void editPlaylistReturnsForbidden() {
        UUID token = UUID.randomUUID();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        int statusCode = sut.editPlaylist(playlistDTO, 1, String.valueOf(token)).getStatus();
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
    }

    @Test
    void verifyGetTracksInPlaylist() {
        UUID token = UUID.randomUUID();
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
        sut.getTracksInPlaylist(1, String.valueOf(token));
        Mockito.verify(mockedTrackService).getAllInPlaylist(1);
    }

    @Test
    void getTracksInPlaylistAsEntity() {
        UUID token = UUID.randomUUID();
        var itemsToReturn = new TracksDTO();
        Mockito.when(mockedTrackService.getAllInPlaylist(1)).thenReturn(itemsToReturn);
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
        var input = sut.getTracksInPlaylist(1, String.valueOf(token));
        assertEquals(input.getEntity(), itemsToReturn);
    }

    @Test
    void getTracksInPlaylistReturnsBadRequest() {
        int statusCode = sut.getTracksInPlaylist(1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void getTracksInPlaylistReturnsForbidden() {
        UUID token = UUID.randomUUID();
        int statusCode = sut.getTracksInPlaylist(1, String.valueOf(token)).getStatus();
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
    }

    @Test
    void verifyRemoveTrackFromPlaylist() {
        UUID token = UUID.randomUUID();
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
        sut.removeTrackFromPlaylist(1, 1, String.valueOf(token));
        Mockito.verify(mockedTrackService).removeTrackFromPlaylist(1, 1);
    }

    @Test
    void removeTrackFromPlaylistReturnsBadRequest() {
        int statusCode = sut.removeTrackFromPlaylist(1, 1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void removeTrackFromPlaylistReturnsForbidden() {
        UUID token = UUID.randomUUID();
        int statusCode = sut.removeTrackFromPlaylist(1, 1, String.valueOf(token)).getStatus();
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
    }

    @Test
    void verifyAddTrackToPlaylist() {
        UUID token = UUID.randomUUID();
        TrackDTO trackDTO = new TrackDTO();
        Mockito.when(mockedUserService.checkIfTokenExists(String.valueOf(token))).thenReturn(true);
        sut.addTrackToPlaylist(trackDTO, 1, String.valueOf(token));
        Mockito.verify(mockedTrackService).addTrackToPlaylist(1, trackDTO.getId(), trackDTO.isOfflineAvailable());
    }

    @Test
    void addTrackToPlaylistReturnsBadRequest() {
        TrackDTO trackDTO = new TrackDTO();
        int statusCode = sut.addTrackToPlaylist(trackDTO, 1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }

    @Test
    void addTrackToPlaylistReturnsForbidden() {
        UUID token = UUID.randomUUID();
        TrackDTO trackDTO = new TrackDTO();
        int statusCode = sut.addTrackToPlaylist(trackDTO, 1, String.valueOf(token)).getStatus();
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
    }
}