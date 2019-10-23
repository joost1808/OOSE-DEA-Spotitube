package nl.oose.dea.data;

import nl.oose.dea.data.dao.TrackDAO;
import nl.oose.dea.domain.pojo.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TrackDAOIntegrationTest {
    TrackDAO trackDAO;
    ConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() {
        trackDAO = new TrackDAO();
        connectionFactory = new ConnectionFactory();
        trackDAO.setConnectionFactory(connectionFactory);
    }

    @Test
    void findAllTracksNotInPlaylistValidPlaylistId() {
        List<Track> expectedTracks = new ArrayList<>();
        expectedTracks.add(new Track(2L, "testTrack3", "performer2", 600, "album2", 17, null, null, false));
        expectedTracks.add(new Track(3L, "testTrack4", "performer2", 700, "album2", 5, null, null, true));
        List<Track> actualTracks = trackDAO.findAllTracksNotInPlaylist(0);
        assertEquals(expectedTracks, actualTracks);

    }

    @Test
    void findAllTracksNotInPlaylistInvalidPlaylistId() {
        List<Track> expectedTracks = new ArrayList<>();
        expectedTracks.add(new Track(2L, "testTrack3", "performer2", 600, "album2", 17, null, null, false));
        expectedTracks.add(new Track(3L, "testTrack4", "performer2", 700, "album2", 5, null, null, true));
        List<Track> actualTracks = trackDAO.findAllTracksNotInPlaylist(1);
        assertNotEquals(expectedTracks, actualTracks);
    }

    @Test
    void findAllTracksInPlaylistValidPlaylistId() {
        List<Track> expectedTracks = new ArrayList<>();
        expectedTracks.add(new Track(0L, "testTrack1", "performer1", 400, "album1", 19, null, null, true));
        expectedTracks.add(new Track(1L, "testTrack2", "performer1", 500, "album1", 5, null, null, false));
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(0);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void findAllTracksInPlaylistInvalidPlaylistId() {
        List<Track> expectedTracks = new ArrayList<>();
        expectedTracks.add(new Track(0L, "testTrack1", "performer1", 400, "album1", 19, null, null, true));
        expectedTracks.add(new Track(1L, "testTrack2", "performer1", 500, "album1", 5, null, null, false));
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(1);
        assertNotEquals(expectedTracks, actualTracks);
    }

    @Test
    @Disabled
    void removeTrackFromPlaylistValidPlaylistIdValidTrackId() {
        List<Track> oldTracks = trackDAO.findAllTracksInPlaylist(0);
        trackDAO.removeTrackFromPlaylist(0, 1);
        List<Track> newTracks = trackDAO.findAllTracksInPlaylist(0);
        assertNotEquals(oldTracks, newTracks);
    }

    @Test
    void removeTrackFromPlaylistValidPlaylistIdInvalidTrackId() {
        List<Track> expectedTracks = trackDAO.findAllTracksInPlaylist(0);
        trackDAO.removeTrackFromPlaylist(0, 4);
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(0);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void removeTrackFromPlaylistInvalidPlaylistIdValidTrackId() {
        List<Track> expectedTracks = trackDAO.findAllTracksInPlaylist(0);
        trackDAO.removeTrackFromPlaylist(2, 1);
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(0);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void removeTrackFromPlaylistInvalidPlaylistIdInvalidTrackId() {
        List<Track> expectedTracks = trackDAO.findAllTracksInPlaylist(0);
        trackDAO.removeTrackFromPlaylist(2, 4);
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(0);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    @Disabled
    void addTrackToPlaylistValidPlaylistIdValidTrackId() {
        List<Track> expectedTracks = trackDAO.findAllTracksInPlaylist(0);
        trackDAO.addTrackToPlaylist(0, 2L, false);
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(0);
        assertNotEquals(expectedTracks, actualTracks);
    }

    @Test
    void addTrackToPlaylistInvalidPlaylistIdValidTrackId() {
        List<Track> expectedTracks = trackDAO.findAllTracksInPlaylist(0);
        trackDAO.addTrackToPlaylist(3, 0L, false);
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(0);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void addTrackToPlaylistValidPlaylistIdInvalidTrackId() {
        List<Track> expectedTracks = trackDAO.findAllTracksInPlaylist(0);
        trackDAO.addTrackToPlaylist(0, 4L, false);
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(0);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void addTrackToPlaylistInvalidPlaylistIdInvalidTrackId() {
        List<Track> expectedTracks = trackDAO.findAllTracksInPlaylist(0);
        trackDAO.addTrackToPlaylist(2, 4L, false);
        List<Track> actualTracks = trackDAO.findAllTracksInPlaylist(0);
        assertEquals(expectedTracks, actualTracks);
    }
}
