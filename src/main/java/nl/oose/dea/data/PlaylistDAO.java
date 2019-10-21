package nl.oose.dea.data;

import nl.oose.dea.domain.Playlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public List<Playlist> getAllPlaylists(String token) {
        try (Connection connection = connectionFactory.create()) {
            var statement = connection.prepareStatement("SELECT id, name, length, owner FROM playlists INNER JOIN userplaylists ON playlists.id = userplaylists.playlistid WHERE userplaylists.usertoken = ?");
            statement.setString(1, token);
            ResultSet rs = statement.executeQuery();
            List<Playlist> playlists = new ArrayList<>();
            while (rs.next()) {
                    Playlist playlist = new Playlist(rs.getInt(1), rs.getString(2), rs.getBoolean(4), new ArrayList<>(), rs.getInt(3));
                    playlists.add(playlist);
            }
            return playlists;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void addPlaylist(String playlistName, String token) {
        try (Connection connection = connectionFactory.create()) {
            if (!checkIfPlaylistExists(playlistName)) {
                var playlistsStatement = connection.prepareStatement("INSERT INTO playlists VALUES (NULL, ?, NULL)");
                playlistsStatement.setString(1, playlistName);
                playlistsStatement.executeUpdate();
            }
            var userPlaylistsStatement = connection.prepareStatement("INSERT INTO userplaylists VALUES (?, ?, ?)");
            userPlaylistsStatement.setString(1, token);
            userPlaylistsStatement.setString(2, String.valueOf(getPlaylistId(playlistName)));
            userPlaylistsStatement.setBoolean(3, !checkIfPlaylistExists(playlistName));
            userPlaylistsStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private Long getPlaylistId(String playlistName) {
        try (Connection connection = connectionFactory.create()) {
            var statement = connection.prepareStatement("SELECT id FROM playlists WHERE name = ?");
            statement.setString(1, playlistName);
            ResultSet rs = statement.executeQuery();
            Long playlistid = 0L;
            while(rs.next()) {
                playlistid = rs.getLong(1);
            }
            return playlistid;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return 0L;
    }

    private boolean checkIfPlaylistExists(String playlistName) {
        try (Connection connection = connectionFactory.create()) {
            var statement = connection.prepareStatement("SELECT name FROM playlists");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                if (rs.getString(1).equals(playlistName)) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public void deletePlaylist(int playlistid, String token) {
        try (Connection connection = connectionFactory.create()) {
            var statement = connection.prepareStatement("DELETE FROM userplaylists WHERE userplaylists.usertoken = ? AND playlistid = ?");
            statement.setString(1, token);
            statement.setString(2, String.valueOf(playlistid));
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void editPlaylist(String name, int playlistid) {
        try (Connection connection = connectionFactory.create()) {
            var statement = connection.prepareStatement("UPDATE playlists SET name = ? WHERE id = ?");
            statement.setString(1, name);
            statement.setString(1, String.valueOf(playlistid));
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
