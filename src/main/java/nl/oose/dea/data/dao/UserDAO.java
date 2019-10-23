package nl.oose.dea.data.dao;

import nl.oose.dea.data.ConnectionFactory;
import nl.oose.dea.domain.iUserDAO;
import nl.oose.dea.domain.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDAO implements iUserDAO {
    private static final UUID uuid = UUID.randomUUID();
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ConnectionFactory connectionFactory;

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public TokenDTO getToken(String user) {
        try (Connection connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("SELECT name, token FROM users WHERE username = ?;");
            statement.setString(1, user);
            ResultSet rs = statement.executeQuery();
            TokenDTO tokenDTO = null;
            while (rs.next()) {
                tokenDTO = new TokenDTO(rs.getString(1), rs.getString(2));
            }
            return tokenDTO;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void setToken(String user) {
        try (Connection connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("UPDATE users SET token = ? WHERE username = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.setString(2, user);
            statement.executeUpdate();
            connection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public boolean verifyUser(String user, String password) {
        try (Connection connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            statement.setString(1, user);
            ResultSet rs = statement.executeQuery();
            String storedPassword = "";
            while (rs.next()) {
                storedPassword = rs.getString(1);
            }
            return storedPassword.equals(password);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean checkIfTokenExists(String token) {
        try (Connection connection = connectionFactory.getConnection();) {
            var statement = connection.prepareStatement("SELECT token FROM users");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                if (rs.getString(1).equals(token)) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
