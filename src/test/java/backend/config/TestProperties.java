package backend.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:backend.properties")
public interface TestProperties extends Config {

    @Key("base_uri")
    String baseUri();
}