package nl.oose.dea.rest;

import nl.oose.dea.domain.services.TrackService;
import nl.oose.dea.domain.dto.TracksDTO;
import nl.oose.dea.rest.resources.TrackResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrackResourceTest {
    private TrackResource sut;
    private TrackService mockedTrackService;

    @BeforeEach
    void setUp() {
        sut = new TrackResource();
        this.mockedTrackService = Mockito.mock(TrackService.class);
        this.sut.setTrackService(mockedTrackService);
    }

    @Test
    void verifyGetTracksNotInPlaylist() {
        UUID token = UUID.randomUUID();
        sut.getTracksNotInPlaylist(1 , String.valueOf(token));
        Mockito.verify(mockedTrackService).getAllNotInPlaylist(1);
    }

    @Test
    void getTracksNotInPlaylistAsEntity() {
        UUID token = UUID.randomUUID();
        var itemsToReturn = new TracksDTO();
        Mockito.when(mockedTrackService.getAllNotInPlaylist(1)).thenReturn(itemsToReturn);
        var input = sut.getTracksNotInPlaylist(1, String.valueOf(token));
        assertEquals(input.getEntity(), itemsToReturn);
    }

    @Test
    void getTracksNotInPlaylistReturnsBadRequest() {
        int statusCode = sut.getTracksNotInPlaylist(1, null).getStatus();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), statusCode);
    }
}