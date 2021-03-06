package ua.willkaxxx.demo.servlet_exhibition.model;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.dao.impl.ConnectionPoolHolder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Logger log = Logger.getLogger(ConfigReader.class);
    private static volatile Properties properties;

    private ConfigReader() {
    }

    public static Properties getProperties() {
        if (properties == null) {
            synchronized (ConfigReader.class) {
                if (properties == null) {
                    try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
                        properties = new Properties();
                        properties.load(input);
                    } catch (IOException ex) {
                        log.error("Error while reading config.properties");
                    }
                }
            }
        }
        return properties;
    }
}
