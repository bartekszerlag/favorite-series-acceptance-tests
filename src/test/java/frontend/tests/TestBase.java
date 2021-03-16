package frontend.tests;

import backend.services.FavoriteSeriesService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static frontend.config.DriverConfig.initDriverConfig;
import static frontend.config.DriverConfig.navigateToPage;
import static frontend.config.DriverManager.closeDriver;
import static frontend.config.PropertiesReader.getProperties;
import static java.lang.String.format;

@Slf4j
public class TestBase {

    static FavoriteSeriesService favoriteSeriesService;

    @BeforeEach
    void displayTestName(TestInfo testInfo) {
        log.info(format("### TEST: %s ###", testInfo.getDisplayName()));
    }

    @BeforeAll
    static void suitSetup() {
        favoriteSeriesService = new FavoriteSeriesService();
        initDriverConfig();
        navigateToPage(getProperties().baseUrl());
    }

    @AfterAll
    static void suitCleanup() {
        closeDriver();
    }
}