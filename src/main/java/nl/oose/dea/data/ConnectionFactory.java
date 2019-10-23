package nl.oose.dea.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private Connection connection;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            create();
            return connection;
        } else if (connection.isClosed()) {
            create();
            return connection;
        } else {
            return connection;
        }
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void create() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
        setConnection(DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString")));
    }
}
