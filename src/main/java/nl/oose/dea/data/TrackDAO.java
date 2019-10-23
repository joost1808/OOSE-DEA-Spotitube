package nl.oose.dea.data;

import nl.oose.dea.domain.iTrackDAO;
import nl.oose.dea.domain.pojo.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO implements iTrackDAO {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ConnectionFactory connectionFactory;

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Track> findAllTracksNotInPlaylist(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM tracks WHERE id IN (SELECT trackid FROM playlisttracks WHERE trackid NOT IN (SELECT trackid FROM playlisttracks WHERE playlistid = ?));");
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while (rs.next()) {
                Track track = new Track(rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
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

    public List<Track> findAllTracksInPlaylist(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM tracks WHERE id IN (SELECT trackid FROM playlisttracks WHERE playlistid = ?)");
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while (rs.next()) {
                Track track = new Track(rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
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

    public void removeTrackFromPlaylist(int playlistid, int trackid) {
        try (Connection connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("DELETE FROM playlisttracks WHERE playlistid = ? AND trackid = ?");
            statement.setString(1, String.valueOf(playlistid));
            statement.setString(2, String.valueOf(trackid));
            statement.executeUpdate();
            connection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void addTrackToPlaylist(int playlistid, Long trackid, boolean offlineAvailable) {
        try (Connection connection = connectionFactory.getConnection()) {
            var statement1 = connection.prepareStatement("UPDATE tracks SET offlineAvailable = ? WHERE id = ?");
            statement1.setBoolean(1, offlineAvailable);
            statement1.setString(2, String.valueOf(trackid));
            statement1.executeUpdate();
            var statement2 = connection.prepareStatement("INSERT INTO playlisttracks VALUES (?, ?)");
            statement2.setString(1, String.valueOf(playlistid));
            statement2.setString(2, String.valueOf(trackid));
            statement2.executeUpdate();
            connection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
