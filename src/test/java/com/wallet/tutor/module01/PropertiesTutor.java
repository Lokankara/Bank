package com.wallet.tutor.module01;

import static com.wallet.tutor.Logger.log;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PropertiesTutor {
    private static final String FILE_PROPS = "files/props.properties";
    private static final String SYSTEM_PROPS = "files/system.properties";
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
        log(getJavaVersion());
        assertTrue(version.startsWith("11."));
    }

    /**
     * Loads the properties file from the files / props.properties folder
     * And returns the downloaded properties
     *
     * @return
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
        log("country=" + props.getProperty("country"));
        log("color=" + props.getProperty("color"));
        assertEquals("Australia", props.getProperty("country"));
        assertEquals("red", props.getProperty("color"));
    }

}
