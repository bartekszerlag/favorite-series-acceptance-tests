package backend.tests;

import backend.pojo.SeriesRequest;
import backend.pojo.SeriesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static backend.tests.data.Series.generateSeries;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeriesApiTests extends TestBase {

    private List<SeriesResponse> seriesList;

    @BeforeEach
    void classSetup() {
        deleteAllSeries();
    }

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

    private void deleteAllSeries() {
        List<Integer> seriesIds;
        List<SeriesResponse> seriesList = favoriteSeriesService.getAllSeries();
        if (seriesList.size() > 0) {
            seriesIds = seriesList.stream().map(SeriesResponse::getId).collect(Collectors.toList());
            seriesIds.forEach(id -> favoriteSeriesService.removeSeries(id));
        }
    }
}
