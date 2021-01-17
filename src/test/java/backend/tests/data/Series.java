package backend.tests.data;

import backend.pojo.SeriesRequest;

public class Series {

    public static SeriesRequest generateSeries() {
        SeriesRequest seriesRequest = new SeriesRequest();
        seriesRequest.setTitle("Friends");
        seriesRequest.setPlatform("Netflix");
        return seriesRequest;
    }
}
