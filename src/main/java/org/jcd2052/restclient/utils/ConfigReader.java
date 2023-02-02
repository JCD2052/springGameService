package org.jcd2052.restclient.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private ConfigReader() {

    }

    private static final String CONFIG_FILE_PATH = "src/main/resources/apiconfig.properties";

    public static String readPropertyFromConfigFile(String propertyField) {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties.getProperty(propertyField);
        } catch (IOException e) {
            throw new IllegalArgumentException(String
                    .format("Couldn't find a property %s in file %s", propertyField,
                            CONFIG_FILE_PATH));
        }
    }
}
