package backend.tests;

import backend.services.FavoriteSeriesService;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

import static common.config.PropertiesReader.getProperties;

public class TestBase {

    static FavoriteSeriesService favoriteSeriesService;

    @BeforeAll
    static void suitSetup() {
        favoriteSeriesService = new FavoriteSeriesService();
        RestAssured.baseURI = getProperties().baseUri();
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }
}