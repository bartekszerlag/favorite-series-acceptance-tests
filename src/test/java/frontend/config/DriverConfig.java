package frontend.config;

import java.util.concurrent.TimeUnit;

import static frontend.config.DriverManager.getDriver;

public class DriverConfig {

    public static void initDriverConfig() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public static void navigateToPage(String url) {
        getDriver().navigate().to(url);
    }
}