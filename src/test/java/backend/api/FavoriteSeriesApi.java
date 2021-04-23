package backend.api;

import backend.pojo.SeriesRequest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class FavoriteSeriesApi {

    @Step("Get all TV series")
    public Response getAllSeries() {
        return when()
                .get("/series")
                .then()
                .extract()
                .response();
    }

    @Step("Add new TV series")
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

    @Step("Remove TV series")
    public Response removeSeries(Integer id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete("/series/{id}")
                .then()
                .extract()
                .response();
    }

    @Step("Get secret message")
    public Response getSecretMessage(String login, String password) {
        return given()
                .auth().basic(login, password)
                .get("/secret")
                .then()
                .extract()
                .response();
    }
}