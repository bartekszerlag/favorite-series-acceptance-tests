package frontend.config;

import org.aeonbits.owner.ConfigFactory;

public class PropertiesReader {

    private static TestProperties properties;

    public PropertiesReader() {
    }

    public static TestProperties getProperties() {
        if (properties == null) {
            properties = ConfigFactory.create(TestProperties.class);
        }
        return properties;
    }
}
