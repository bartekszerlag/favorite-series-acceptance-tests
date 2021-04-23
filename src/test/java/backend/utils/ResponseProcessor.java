package backend.utils;

import backend.pojo.SeriesResponse;
import io.restassured.response.Response;

import java.util.List;

public class ResponseProcessor {

    public List<SeriesResponse> getSeriesList(Response response) {
        return response
                .jsonPath()
                .getList(".", SeriesResponse.class);
    }
}