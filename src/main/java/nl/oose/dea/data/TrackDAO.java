package nl.oose.dea.data;

import nl.oose.dea.domain.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Track> findAllTracksFromPlaylist(int playlistId) {
        try {
            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
            var statement = connection.prepareStatement("SELECT * FROM tracks WHERE id IN (SELECT trackid FROM playlisttracks WHERE playlistid = ?)");
            statement.setInt(1, playlistId);
            ResultSet rs = statement.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while (rs.next()) {
                Track track = new Track(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getBoolean(9));
                tracks.add(track);
            }
            return tracks;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Track> findAllTracks(int id) {
        try {
            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
            var statement = connection.prepareStatement("SELECT * FROM tracks WHERE id IN (SELECT trackid FROM playlisttracks WHERE playistid != ?)");
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while (rs.next()) {
                Track track = new Track(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getBoolean(9));
                tracks.add(track);
            }
            return tracks;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
