package frontend.tests;

import backend.services.FavoriteSeriesService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static frontend.config.DriverConfig.initDriverConfig;
import static frontend.config.DriverConfig.navigateToPage;
import static frontend.config.DriverManager.closeDriver;
import static frontend.config.PropertiesReader.getProperties;
import static java.lang.String.format;

public class TestBase {

    private final Logger logger = LoggerFactory.getLogger(TestBase.class);

    static FavoriteSeriesService favoriteSeriesService;

    @BeforeEach
    void displayTestName(TestInfo testInfo) {
        logger.info(format("### TEST: %s ###", testInfo.getDisplayName()));
    }

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
