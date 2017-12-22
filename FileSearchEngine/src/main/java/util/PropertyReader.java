package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static Properties props;

    static {

        String resourceName = "application.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String propertyName) {
        String value = null;
        if (props.containsKey(propertyName)) {
            value = props.getProperty(propertyName);
        }
        return value;
    }
}