package backend.services;

import backend.pojo.SeriesRequest;
import backend.pojo.SeriesResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class FavoriteSeriesService {

    public List<SeriesResponse> getAllSeries() {
        return when()
                .get("/series")
                .then()
                .extract()
                .jsonPath()
                .getList(".", SeriesResponse.class);
    }

    public Response addSeries(SeriesRequest series) {
        return given()
                .contentType(ContentType.JSON)
                .body(series)
                .when()
                .post("/series")
                .then()
                .extract()
                .response();
    }

    public Response removeSeries(Integer id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete("/series/{id}")
                .then()
                .extract()
                .response();
    }
}