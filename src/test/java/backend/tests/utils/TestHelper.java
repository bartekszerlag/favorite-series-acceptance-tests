package backend.tests.utils;

import backend.pojo.SeriesRequest;
import backend.pojo.SeriesResponse;
import backend.services.FavoriteSeriesService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestHelper {

    private static final FavoriteSeriesService favoriteSeriesService = new FavoriteSeriesService();

    public static void deleteAllSeries() {
        List<Integer> seriesIds;
        List<SeriesResponse> seriesList = favoriteSeriesService.getAllSeries();
        if (seriesList.size() > 0) {
            seriesIds = seriesList.stream().map(SeriesResponse::getId).collect(Collectors.toList());
            seriesIds.forEach(favoriteSeriesService::removeSeries);
        }
    }

    public static void fillSeriesList() {
        List<SeriesResponse> seriesList = favoriteSeriesService.getAllSeries();
        if (seriesList.size() == 0) {
            IntStream.range(0, 5).mapToObj(i -> getSeriesList().get(i)).forEach(favoriteSeriesService::addSeries);
        }
    }

    private static List<SeriesRequest> getSeriesList() {
        return List.of(
                new SeriesRequest("Friends", "Netflix"),
                new SeriesRequest("Narcos", "Netflix"),
                new SeriesRequest("True Detective", "HBO"),
                new SeriesRequest("Klan", "TVP"),
                new SeriesRequest("Ranczo", "TVP")
        );
    }
}
