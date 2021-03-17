package common.config;

import frontend.config.BrowserType;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:frontend.properties",
        "classpath:backend.properties",
})
public interface TestProperties extends Config {

    @Key("base_uri")
    String baseUri();

    @Key("base_url")
    String baseUrl();

    @Key("driver")
    BrowserType driver();
}