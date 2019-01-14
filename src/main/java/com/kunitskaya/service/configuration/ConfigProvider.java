package com.kunitskaya.service.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigProvider {

    public static Logger logger = LogManager.getLogger(ConfigProvider.class);
    private static final String PROPERTIES_PATH = "src/main/resources/jdbc.properties";
    private static ConfigProvider instance;

    private Properties properties = new Properties();

    private ConfigProvider() {
        loadProperties();
    }

    public static ConfigProvider getInstance() {
        if (instance == null) {
            instance = new ConfigProvider();
        }
        return instance;
    }


    private void loadProperties() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Successfully loaded properties from: " + PROPERTIES_PATH);
    }

    public String getDBUsername() {
        return properties.getProperty("jdbc.username");
    }

    public String getDBPassword() {
        return properties.getProperty("jdbc.password");
    }

    public String getDBUrl() {
        return properties.getProperty("jdbc.url");
    }
}
