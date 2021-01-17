package frontend.tests;

import backend.services.FavoriteSeriesService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static frontend.config.DriverConfig.initDriverConfig;
import static frontend.config.DriverConfig.navigateToPage;
import static frontend.config.DriverManager.closeDriver;
import static frontend.config.PropertiesReader.getProperties;

public class TestBase {

    static FavoriteSeriesService favoriteSeriesService;

    @BeforeAll
    static void setup() {
        favoriteSeriesService = new FavoriteSeriesService();
        initDriverConfig();
        navigateToPage(getProperties().baseUrl());
    }

    @AfterAll
    static void cleanup() {
        closeDriver();
    }
}
