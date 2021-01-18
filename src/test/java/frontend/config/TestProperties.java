package frontend.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:frontend.properties")
public interface TestProperties extends Config {

    @Key("base_url")
    String baseUrl();

    @Key("driver")
    BrowserType driver();
}
