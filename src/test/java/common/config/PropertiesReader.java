package common.config;

import org.aeonbits.owner.ConfigCache;

public class PropertiesReader {

    public static TestProperties getProperties() {
        return ConfigCache.getOrCreate(TestProperties.class);
    }
}