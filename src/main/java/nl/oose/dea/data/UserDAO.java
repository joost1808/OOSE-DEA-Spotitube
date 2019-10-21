package nl.oose.dea.data;

import nl.oose.dea.rest.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDAO {
    private static final UUID uuid = UUID.randomUUID();
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public TokenDTO getToken(String user) {
        try (Connection connection = connectionFactory.create()) {
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
        try (Connection connection = connectionFactory.create()) {
            var statement = connection.prepareStatement("UPDATE users SET token = ? WHERE username = ?");
            statement.setString(1, String.valueOf(uuid));
            statement.setString(2, user);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public boolean verifyUser(String user, String password) {
        try (Connection connection = connectionFactory.create()) {
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
}
