package org.testtask.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyHandler {

    private static final Logger logger = Logger.getLogger(PropertyHandler.class);

    private static volatile PropertyHandler instance = null;
    private final Properties props = new Properties();

    private PropertyHandler() {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            props.load(inputStream);
        } catch (IOException e) {
            logger.error(e);
            System.exit(1);
        }
    }

    public static PropertyHandler getInstance() {
        if (instance == null) {
            synchronized (PropertyHandler.class) {
                if (instance == null) {
                    instance = new PropertyHandler();
                }
            }
        }
        return instance;
    }


    public String getValue(String propKey) {
        return this.props.getProperty(propKey);
    }
}
