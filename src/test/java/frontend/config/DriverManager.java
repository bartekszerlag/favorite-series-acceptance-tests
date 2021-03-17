package frontend.config;

import org.openqa.selenium.WebDriver;

import static common.config.PropertiesReader.getProperties;
import static frontend.config.BrowserManager.getBrowserDriver;

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