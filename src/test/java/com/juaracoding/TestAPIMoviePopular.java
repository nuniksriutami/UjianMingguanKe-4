package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestAPIMoviePopular {
    String endpoint = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";
    String myToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNWMxOWZhZWNiNjdjYWM3ZTQwOGM0Zjk5NmZiNzVkMCIsInN1YiI6IjY1ZDg4YWVmNDBkMGZlMDE4NWExNDViOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tX1QuKzF4HAWM_tYr_ikzwlLgZxZGp1nb45yuZ1ET4s";
    @Test
    public void testGetMoviePopular(){

        given()
                .header("Authorization", myToken)
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("results.id[0]", equalTo(1072790))
                .log().all();


    }
    @Test
    public void testGetListMoviePopular(){
        Response response = RestAssured.get(endpoint);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeader("Content-Type"));
        System.out.println(response.getTime());
        Assert.assertEquals(response.getStatusCode(),401);
    }
    @Test
    public void testPostTitleMovie(){
        JSONObject request = new JSONObject();
        request.put("title", "Aquaman and the Lost Kingdom");
        System.out.println(request.toJSONString());
        given()
                .header("Content-Type", "application/json")
                .queryParam("language","end-US")
                .body(request.toJSONString())
                .when()
                .post("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
                .then()
                .statusCode(401)
                .log().all();
    }
}
