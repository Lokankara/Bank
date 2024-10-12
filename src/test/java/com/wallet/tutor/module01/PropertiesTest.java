package com.wallet.tutor.module01;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class PropertiesTest {
    private static final String FILE_PROPS = "src/main/resources/props.properties";
    private static final String SYSTEM_PROPS = "src/main/resources/system.properties";
    private final Properties properties = new Properties();

    /**
     * Returns the value of the system property java.version
     */
    public String getJavaVersion() {

        return getProperties(SYSTEM_PROPS).getProperty("java.runtime.version");
    }

    @Test
    public void testJavaVersion() {
        String version = getJavaVersion();
        log.info(getJavaVersion());
        assertTrue(version.startsWith("11."));
    }

    /**
     * Loads the properties file from the files / props.properties folder
     * And returns the downloaded properties
     */
    public Properties getProperties() {
        return getProperties(FILE_PROPS);
    }

    public Properties getProperties(String props) {

        try (FileInputStream inputStream = new FileInputStream(props)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
        return properties;
    }


    @Test
    public void testGetProperties() {
        Properties props = getProperties();
        log.info("country=" + props.getProperty("country"));
        log.info("color=" + props.getProperty("color"));
        assertEquals("Australia", props.getProperty("country"));
        assertEquals("red", props.getProperty("color"));
    }
}
