package nl.oose.dea.domain.services;

import nl.oose.dea.data.dao.TrackDAO;
import nl.oose.dea.domain.pojo.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackServiceTest {
    private TrackService sut;
    private TrackDAO mockedTrackDAO;

    @BeforeEach
    void setUp() {
        sut = new TrackService();
        this.mockedTrackDAO = Mockito.mock(TrackDAO.class);
        this.sut.setTrackDAO(mockedTrackDAO);
    }

    @Test
    void verifyGetAllNotInPlaylist() {
        sut.getAllNotInPlaylist(1);
        Mockito.verify(mockedTrackDAO).findAllTracksNotInPlaylist(1);
    }

    @Test
    void getAllNotInPlaylistAsEntity() {
        List<Track> tracks = new ArrayList<>();
        Mockito.when(mockedTrackDAO.findAllTracksNotInPlaylist(1)).thenReturn(tracks);
        var input = sut.getAllNotInPlaylist(1);
        assertEquals(input.getTracks(), tracks);
    }

    @Test
    void verifyGetAllInPlaylist() {
        sut.getAllInPlaylist(1);
        Mockito.verify(mockedTrackDAO).findAllTracksInPlaylist(1);
    }

    @Test
    void getAllInPlaylistAsEntity() {
        List<Track> tracks = new ArrayList<>();
        Mockito.when(mockedTrackDAO.findAllTracksInPlaylist(1)).thenReturn(tracks);
        var input = sut.getAllInPlaylist(1);
        assertEquals(input.getTracks(), tracks);
    }

    @Test
    void verifyRemoveTrackFromPlaylist() {
        sut.removeTrackFromPlaylist(1, 1);
        Mockito.verify(mockedTrackDAO).removeTrackFromPlaylist(1, 1);
    }

    @Test
    void verifyAddTrackToPlaylist() {
        sut.addTrackToPlaylist(1, 1L, false);
        Mockito.verify(mockedTrackDAO).addTrackToPlaylist(1, 1L, false);
    }
}