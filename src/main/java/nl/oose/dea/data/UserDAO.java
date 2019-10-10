package nl.oose.dea.data;

import nl.oose.dea.rest.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TokenDTO getToken(String user) {
        try {
            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
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
}
