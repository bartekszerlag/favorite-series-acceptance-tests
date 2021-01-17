package frontend.config;

import org.openqa.selenium.WebDriver;

import static frontend.config.BrowserManager.getBrowserDriver;
import static frontend.config.PropertiesReader.getProperties;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = getBrowserDriver(getProperties().driver());
        }
        return driver;
    }

    public static void closeDriver() {
        driver.close();
        driver = null;
    }
}
