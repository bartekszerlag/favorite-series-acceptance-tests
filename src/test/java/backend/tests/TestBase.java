package backend.tests;

import backend.api.FavoriteSeriesApi;
import backend.utils.ResponseProcessor;
import common.utils.TestHelper;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

import static common.config.PropertiesReader.getProperties;

public class TestBase {

    static FavoriteSeriesApi favoriteSeriesApi;
    static ResponseProcessor responseProcessor;
    static TestHelper testHelper;

    @BeforeAll
    static void suitSetup() {
        favoriteSeriesApi = new FavoriteSeriesApi();
        responseProcessor = new ResponseProcessor();
        testHelper = new TestHelper(responseProcessor, favoriteSeriesApi);
        RestAssured.baseURI = getProperties().baseUri();
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }
}