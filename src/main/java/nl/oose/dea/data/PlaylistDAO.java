package nl.oose.dea.data;

import nl.oose.dea.domain.Playlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Playlist> getAllPlaylists(String token) {
        try {
            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
            var statement = connection.prepareStatement("SELECT * FROM playlists INNER JOIN userplaylists ON playlists.id = userplaylists.playlistid WHERE userplaylists.usertoken = ?");
            statement.setString(1, token);
            ResultSet rs = statement.executeQuery();
            List<Playlist> playlists = new ArrayList<>();
            while (rs.next()) {
                Playlist playlist = new Playlist(rs.getInt(1), rs.getString(2), rs.getBoolean(3), new ArrayList<>(), rs.getInt(4));
                playlists.add(playlist);
            }
            return playlists;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void deletePlaylist(int id, String token) {
        try {
            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
            var statement = connection.prepareStatement("DELETE FROM userplaylists WHERE userplaylists.usertoken = ? AND playlistid = ?");
            statement.setString(1, token);
            statement.setString(2, String.valueOf(id));
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void editPlaylist(String name, int id) {
        try {
            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
            var statement = connection.prepareStatement("UPDATE playlists SET name = ? WHERE id = ?");
            statement.setString(1, name);
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
