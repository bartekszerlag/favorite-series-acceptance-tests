package backend.services;

import backend.pojo.SeriesRequest;
import backend.pojo.SeriesResponse;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class FavoriteSeriesService {

    @Step("Get all TV series")
    public List<SeriesResponse> getAllSeries() {
        return when()
                .get("/series")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", SeriesResponse.class);
    }

    @Step("Add new TV series")
    public Response addSeries(SeriesRequest series) {
        return given()
                .contentType(ContentType.JSON)
                .body(series)
                .when()
                .post("/series")
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

    @Step("Remove TV series")
    public Response removeSeries(Integer id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete("/series/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}