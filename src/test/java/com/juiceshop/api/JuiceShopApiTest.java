package com.juiceshop.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class JuiceShopApiTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI="http://juice-shop:3000";
    }

    @Test
    public void createUser(){
        String payload="{\n" +
                "    \"email\": \"abc1213@gmail.com\",\n" +
                "    \"passwordRepeat\": \"Password@123\",\n" +
                "    \"securityQuestion\": {\n" +
                "        \"id\": 1,\n" +
                "        \"question\": \"Your eldest siblings middle name?\",\n" +
                "        \"createdAt\": \"2020-06-03T14:06:01.000Z\",\n" +
                "        \"updatedAt\": \"2020-06-03T14:06:01.000Z\"\n" +
                "    },\n" +
                "    \"securityAnswer\": \"asdf\"\n" +
                "}";
        Response response=RestAssured
                .given()
                .header("Content-Type","application/json")
                .when()
                .body(payload)
                .log().all()
                .post("api/Users")
                .then()
                .statusCode(201)
                .extract()
                .response();
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.get("data.role").toString());

    }
    @Test
    public void searchProduct(){
        Response response=   RestAssured
                .given()
                .header("Content-Type","application/json")
                .log().all()
                .get("/rest/products/search?q=apple")
                .then()
                .statusCode(200)
                .extract()
                .response();
        JsonPath jsonPath = response.jsonPath();
        ArrayList<String> expected=new ArrayList();
        expected.add("Apple Juice (1000ml)");
        expected.add("Apple Pomace");
        ArrayList<String> actual=jsonPath.get("data.name");
        Assert.assertEquals(expected,actual);
    }
}
