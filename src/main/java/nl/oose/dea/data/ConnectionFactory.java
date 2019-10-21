package nl.oose.dea.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public final Connection create() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
        return DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
    }
}
