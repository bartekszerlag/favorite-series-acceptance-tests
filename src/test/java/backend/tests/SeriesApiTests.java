package backend.tests;

import backend.pojo.SeriesRequest;
import backend.pojo.SeriesResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static backend.tests.utils.TestHelper.deleteAllSeries;
import static backend.tests.utils.TestHelper.fillSeriesList;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeriesApiTests extends TestBase {

    private List<SeriesResponse> seriesList;

    @BeforeEach
    void classSetup() {
        deleteAllSeries();
    }

    @Test
    void shouldAddOnlyOneSeriesWithTheSameTitleToEmptyList() {
        //given
        Response response;
        SeriesRequest series = new SeriesRequest("Ozark", "Netflix");

        //when
        response = favoriteSeriesService.addSeries(series);
        seriesList = favoriteSeriesService.getAllSeries();

        //then
        assertEquals(201, response.statusCode());
        assertEquals(1, seriesList.size());

        //when
        response = favoriteSeriesService.addSeries(series);
        seriesList = favoriteSeriesService.getAllSeries();

        //then
        assertEquals(409, response.statusCode());
        assertEquals(1, seriesList.size());
    }

    @Test
    void shouldNotAddSeriesToFullList() {
        //given
        fillSeriesList();
        SeriesRequest series = new SeriesRequest("Ozark", "Netflix");

        //when
        Response response = favoriteSeriesService.addSeries(series);
        String errorMessage = response.path("message");

        //then
        assertEquals(403, response.statusCode());
        assertEquals("Series limit is: 5", errorMessage);
    }

    @Test
    void shouldRemoveSeriesFromListWithOneElement() {
        //given
        favoriteSeriesService.addSeries(new SeriesRequest("Ozark", "Netflix"));
        int seriesId = favoriteSeriesService.getAllSeries().get(0).getId();

        //when
        Response response = favoriteSeriesService.removeSeries(seriesId);
        seriesList = favoriteSeriesService.getAllSeries();

        //then
        assertEquals(200, response.statusCode());
        assertEquals(0, seriesList.size());
    }

    @Test
    void shouldNotRemoveSeriesFromEmptyList() {
        //given
        int seriesId = 1;

        //when
        Response response = favoriteSeriesService.removeSeries(seriesId);
        String errorMessage = response.path("message");

        //then
        assertEquals(404, response.statusCode());
        assertEquals(format("Series with id: %d not exist", seriesId), errorMessage);
    }
}