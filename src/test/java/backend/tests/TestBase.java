package backend.tests;

import backend.config.TestProperties;
import backend.services.FavoriteSeriesService;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    static FavoriteSeriesService favoriteSeriesService;

    @BeforeAll
    static void suitSetup() {
        favoriteSeriesService = new FavoriteSeriesService();
        TestProperties properties = ConfigFactory.create(TestProperties.class);
        RestAssured.baseURI = properties.baseUri();
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }
}
