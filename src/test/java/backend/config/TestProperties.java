package backend.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:backend.properties")
public interface TestProperties extends Config {

    @Key("base_uri")
    String baseUri();
}
