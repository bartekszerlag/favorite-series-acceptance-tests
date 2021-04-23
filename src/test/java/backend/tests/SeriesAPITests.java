package backend.tests;

import backend.pojo.SeriesRequest;
import backend.pojo.SeriesResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static common.config.PropertiesReader.getProperties;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class SeriesAPITests extends TestBase {

    private List<SeriesResponse> seriesList;
    private SeriesRequest series = new SeriesRequest("Ozark", "Netflix");

    @BeforeEach
    void classSetup() {
        testHelper.deleteAllSeries();
    }

    @Severity(BLOCKER)
    @Description("New TV series should be added to empty list")
    @Test
    void shouldAddSeriesToEmptyList() {
        //given
        Response response;

        //when
        response = favoriteSeriesApi.addSeries(series);

        //then
        assertThat(response.statusCode()).isEqualTo(201);

        //when
        response = favoriteSeriesApi.getAllSeries();
        seriesList = responseProcessor.getSeriesList(response);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(seriesList.size()).isEqualTo(1);
    }

    @Severity(CRITICAL)
    @Description("Only one TV series with specific title should be added to empty list")
    @Test
    void shouldAddOnlyOneSeriesWithTheSameTitleToEmptyList() {
        //given
        Response response;

        //when
        response = favoriteSeriesApi.addSeries(series);

        //then
        assertThat(response.statusCode()).isEqualTo(201);

        //when
        response = favoriteSeriesApi.getAllSeries();
        seriesList = responseProcessor.getSeriesList(response);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(seriesList.size()).isEqualTo(1);

        //when
        response = favoriteSeriesApi.addSeries(series);

        //then
        assertThat(response.statusCode()).isEqualTo(409);

        //when
        response = favoriteSeriesApi.getAllSeries();
        seriesList = responseProcessor.getSeriesList(response);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(seriesList.size()).isEqualTo(1);
    }

    @Severity(CRITICAL)
    @Description("New TV series should not be added to full list")
    @Test
    void shouldNotAddSeriesToFullList() {
        //given
        Response response;
        testHelper.fillSeriesList();

        //when
        response = favoriteSeriesApi.getAllSeries();
        seriesList = responseProcessor.getSeriesList(response);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(seriesList.size()).isEqualTo(5);

        //when
        response = favoriteSeriesApi.addSeries(series);

        //then
        assertThat(response.statusCode()).isEqualTo(403);
        assertThat(response.path("message").toString()).isEqualTo("Series limit is: 5");
    }

    @Severity(BLOCKER)
    @Description("New TV series should be removed")
    @Test
    void shouldRemoveSeriesFromListWithOneElement() {
        //given
        Response response;

        //when
        response = favoriteSeriesApi.addSeries(series);
        int seriesId = response.path("id");

        //then
        assertThat(response.statusCode()).isEqualTo(201);

        //when
        response = favoriteSeriesApi.getAllSeries();
        seriesList = responseProcessor.getSeriesList(response);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(seriesList.size()).isEqualTo(1);

        //when
        response = favoriteSeriesApi.removeSeries(seriesId);

        //then
        assertThat(response.statusCode()).isEqualTo(204);

        //when
        response = favoriteSeriesApi.getAllSeries();
        seriesList = responseProcessor.getSeriesList(response);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(seriesList.size()).isEqualTo(0);
    }

    @Severity(CRITICAL)
    @Description("TV series should not be removed from empty list")
    @Test
    void shouldNotRemoveSeriesFromEmptyList() {
        //given
        Response response;
        int seriesId = 1;

        //when
        response = favoriteSeriesApi.getAllSeries();
        seriesList = responseProcessor.getSeriesList(response);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(seriesList.size()).isEqualTo(0);

        //when
        response = favoriteSeriesApi.removeSeries(seriesId);

        //then
        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(response.path("message").toString()).isEqualTo(format("Series with id: %d not exist", seriesId));
    }

    @Severity(CRITICAL)
    @Description("Secret message should be returned for valid credentials")
    @Test
    void shouldGetSecretMessageWithValidCredentials() {
        //given
        String login = getProperties().authLogin();
        String password = getProperties().authPassword();
        
        //when
        Response response = favoriteSeriesApi.getSecretMessage(login, password);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body().asString()).contains("If you are bored of watching TV series try:");
    }

    @Severity(CRITICAL)
    @Description("Secret message should not be returned for invalid credentials")
    @Test
    void shouldNotGetSecretMessageWithInvalidCredentials() {
        //when
        Response response = favoriteSeriesApi.getSecretMessage("fake", "fake");

        //then
        assertThat(response.statusCode()).isEqualTo(401);
    }
}
