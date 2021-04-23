package common.utils;

import backend.api.FavoriteSeriesApi;
import backend.pojo.SeriesRequest;
import backend.pojo.SeriesResponse;
import backend.utils.ResponseProcessor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestHelper {

    private ResponseProcessor responseProcessor;
    private FavoriteSeriesApi favoriteSeriesService;

    public TestHelper(ResponseProcessor responseProcessor, FavoriteSeriesApi favoriteSeriesService) {
        this.responseProcessor = responseProcessor;
        this.favoriteSeriesService = favoriteSeriesService;
    }

    public void deleteAllSeries() {
        List<SeriesResponse> seriesList = getSeriesList();
        if (seriesList.size() > 0) {
            seriesList
                    .stream()
                    .map(SeriesResponse::getId)
                    .collect(Collectors.toList())
                    .forEach(favoriteSeriesService::removeSeries);
        }
    }

    public void fillSeriesList() {
        List<SeriesResponse> seriesList = getSeriesList();
        if (seriesList.size() == 0) {
            IntStream
                    .range(0, 5)
                    .mapToObj(i -> generateSeries().get(i))
                    .forEach(favoriteSeriesService::addSeries);
        }
    }

    private static List<SeriesRequest> generateSeries() {
        return List.of(
                new SeriesRequest("Friends", "Netflix"),
                new SeriesRequest("Narcos", "Netflix"),
                new SeriesRequest("True Detective", "HBO"),
                new SeriesRequest("Klan", "TVP"),
                new SeriesRequest("Ranczo", "TVP")
        );
    }

    private List<SeriesResponse> getSeriesList() {
        return responseProcessor.getSeriesList(favoriteSeriesService.getAllSeries());
    }
}