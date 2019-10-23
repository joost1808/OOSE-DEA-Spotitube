package nl.oose.dea.data;

import nl.oose.dea.data.dao.PlaylistDAO;
import nl.oose.dea.domain.pojo.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOIntegrationTest {
    PlaylistDAO playlistDAO;
    ConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() {
        playlistDAO = new PlaylistDAO();
        connectionFactory = new ConnectionFactory();
        playlistDAO.setConnectionFactory(connectionFactory);
    }

    @Test
    void getAllPlaylistsValidToken() {
        List<Playlist> expectedList = new ArrayList<>();
        expectedList.add(new Playlist(0, "testPlaylist1", true, new ArrayList<>(), 0));
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    void getAllPlaylistsInvalidToken() {
        List<Playlist> expectedList = new ArrayList<>();
        expectedList.add(new Playlist(0, "testPlaylist1", true, new ArrayList<>(), 0));
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1-1-1");
        assertNotEquals(expectedList, actualList);
    }

    @Test
    @Disabled
    void addPlaylistValidPlaylistNameValidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.addPlaylist("testPlaylist3", "1234-1234-1234");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertNotEquals(expectedList, actualList);
    }

    @Test
    void addPlaylistValidPlaylistNameInvalidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.addPlaylist("testPlaylist3", "1-1-1");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    void addPlaylistNullPlaylistNameValidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.addPlaylist(null, "1234-1234-1234");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    void addPlaylistNullPlaylistNameInvalidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.addPlaylist(null, "1-1-1");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    @Disabled
    void addPlaylistPlaylistNameAlreadyExistsValidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.addPlaylist("testPlaylist2", "1234-1234-1234");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertNotEquals(expectedList, actualList);
    }

    @Test
    @Disabled
    void deletePlaylistValidPlaylistIdValidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.deletePlaylist(0, "1234-1234-1234");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertNotEquals(expectedList, actualList);
    }

    @Test
    void deletePlaylistInvalidPlaylistIdValidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.deletePlaylist(2, "1234-1234-1234");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    void deletePlaylistValidPlaylistIdInvalidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.deletePlaylist(0, "1-1-1");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    void deletePlaylistInvalidPlaylistIdInvalidToken() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.deletePlaylist(2, "1-1-1");
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    @Disabled
    void editPlaylistValidNameValidPlaylistId() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.editPlaylist("differentName", 0);
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertNotEquals(expectedList, actualList);
    }

    @Test
    void editPlaylistNullNameValidPlaylistId() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.editPlaylist(null, 0);
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    void editPlaylistValidNameInvalidPlaylistId() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.editPlaylist("differentName", 2);
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }

    @Test
    void editPlaylistNullNameInvalidPlaylistId() {
        List<Playlist> expectedList = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlistDAO.editPlaylist(null, 2);
        List<Playlist> actualList = playlistDAO.getAllPlaylists("1234-1234-1234");
        assertEquals(expectedList, actualList);
    }
}
