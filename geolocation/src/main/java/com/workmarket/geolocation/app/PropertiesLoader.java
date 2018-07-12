package com.workmarket.geolocation.app;

import com.google.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private final Properties properties = new Properties();

    @Inject
    public PropertiesLoader() {
        try (InputStream is = this.getClass().getResourceAsStream("/application.properties")) {
            if(is != null) {
                properties.load(is);
            }
        } catch (IOException e) {
            // ignore
        }
    }

    public Integer getPropertyAsInt(String property, int defaultValue) {
        return Integer.parseInt(properties.getProperty(property, String.valueOf(defaultValue)));
    }
}
