package frontend.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:frontend.properties")
public interface TestProperties extends Config {

    @Key("base_url")
    String baseUrl();

    @Key("driver")
    BrowserType driver();
}
