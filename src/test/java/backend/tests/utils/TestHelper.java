package backend.tests.utils;

import backend.pojo.SeriesResponse;
import backend.services.FavoriteSeriesService;

import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static void deleteAllSeries() {
        FavoriteSeriesService favoriteSeriesService = new FavoriteSeriesService();
        List<Integer> seriesIds;
        List<SeriesResponse> seriesList = favoriteSeriesService.getAllSeries();
        if (seriesList.size() > 0) {
            seriesIds = seriesList.stream().map(SeriesResponse::getId).collect(Collectors.toList());
            seriesIds.forEach(favoriteSeriesService::removeSeries);
        }
    }
}
