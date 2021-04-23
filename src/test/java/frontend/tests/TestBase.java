package frontend.tests;

import backend.api.FavoriteSeriesApi;
import backend.utils.ResponseProcessor;
import common.utils.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static common.config.PropertiesReader.getProperties;
import static frontend.config.DriverConfig.initDriverConfig;
import static frontend.config.DriverConfig.navigateToPage;
import static frontend.config.DriverManager.closeDriver;
import static frontend.config.DriverManager.getDriver;
import static java.lang.String.format;

@Slf4j
public class TestBase {

    static FavoriteSeriesApi favoriteSeriesService;
    static ResponseProcessor responseProcessor;
    static TestHelper testHelper;

    @BeforeAll
    static void suitSetup() {
        favoriteSeriesService = new FavoriteSeriesApi();
        responseProcessor = new ResponseProcessor();
        testHelper = new TestHelper(responseProcessor, favoriteSeriesService);
        initDriverConfig();
        navigateToPage(getProperties().baseUrl());
    }

    @BeforeEach
    void classSetup(TestInfo testInfo) {
        log.info(format("### TEST: %s ###", testInfo.getDisplayName()));
        testHelper.deleteAllSeries();
        getDriver().navigate().refresh();
    }

    @AfterAll
    static void suitCleanup() {
        closeDriver();
    }
}