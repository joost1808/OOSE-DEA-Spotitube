package nl.oose.dea.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public final class DatabaseProperties {
    static final Logger logger = LoggerFactory.getLogger(DatabaseProperties.class);
    private static Properties properties = new Properties();

    public static String getDatabaseProperty(String key) {
        try {
            properties.load(DatabaseProperties.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return properties.getProperty(key);
    }
}
