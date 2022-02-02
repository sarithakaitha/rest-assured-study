package com.xyzcorp;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FruitTest {
    //Rest-Assured
    // [{"description":"Winter fruit","name":"Apple"},
    //  {"description":"Tropical fruit","name":"Pineapple"}]
    @Test
    public void testGetFruits() {
        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .when()
                .get("https://staging.tiered-planet.net/mild-temper/fruits")
                .then()
                .assertThat()
                .body("[0].description", equalTo("Winter fruit"))
                .body("[1].description", equalTo("Tropical fruit"));
    }
    @Test
    public void testPostNewFruit() {
        JSONObject mangoObject = new JSONObject()
                .put("name", "mango")
                .put("description", "a seasonal fruit");

        System.out.println(mangoObject);

        given()
                .relaxedHTTPSValidation()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(mangoObject.toString())
                .when()
                .post("https://staging.tiered-planet.net/mild-temper/fruits")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
