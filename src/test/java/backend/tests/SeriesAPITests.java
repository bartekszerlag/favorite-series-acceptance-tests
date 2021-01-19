package backend.tests;

import backend.pojo.SeriesRequest;
import backend.pojo.SeriesResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static backend.tests.data.Series.generateSeries;
import static backend.tests.utils.TestHelper.deleteAllSeries;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeriesAPITests extends TestBase {

    private List<SeriesResponse> seriesList;

    @BeforeEach
    void classSetup() {
        deleteAllSeries();
    }

    @Severity(BLOCKER)
    @Description("New TV series should be added and GET method should return it")
    @Test
    void shouldAddSeries() {
        //given
        SeriesRequest series = generateSeries();

        //when
        favoriteSeriesService.addSeries(series);

        //then
        seriesList = favoriteSeriesService.getAllSeries();
        assertEquals(1, seriesList.size());
    }

    @Severity(BLOCKER)
    @Description("New TV series should be removed and GET method should return empty list")
    @Test
    void shouldRemoveSeries() {
        //given
        favoriteSeriesService.addSeries(generateSeries());
        int seriesId = favoriteSeriesService.getAllSeries().get(0).getId();

        //when
        favoriteSeriesService.removeSeries(seriesId);

        //then
        seriesList = favoriteSeriesService.getAllSeries();
        assertEquals(0, seriesList.size());
    }
}
