package apiTesting;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class testGetOrderDetails {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = "https://api.example.com";
        RestAssured.basePath = "/api";
    }

    @Test
    public void verifyOrderStatusIsPaid() {

        Response response = RestAssured
                .given()
                .header("Accept", "application/json")
                .when()
                .get("/orders/ORD-1001");

        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");

        Assert.assertEquals(
                response.jsonPath().getString("status"),
                "PAID",
                "Order status mismatch");

        Assert.assertEquals(
                response.jsonPath().getString("order_id"),
                "ORD-1001",
                "Order ID mismatch");

        System.out.println(response.getBody().asPrettyString());
    }
}