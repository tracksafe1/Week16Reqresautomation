package org.example.homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GCPD1 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    @Test
    public void getList() {

        given().log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .statusCode(200);

    }
    @Test
    public void getSingleUser() {

        given().log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then().log().all()
                .statusCode(200);

    }
    @Test
    public void createUser() {

        String jsonData = "{\n" +
                "    \"name\": \"shikha\",\n" +
                "    \"job\": \"Developer\"\n" +
                "}";

        validatableResponse = given()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);


        System.out.println(validatableResponse.extract().asPrettyString());
    }
    @Test
    public void updateEmployeeBdd() {

        String jsonData = "{\n" +
                "    \"name\": \"shikha\",\n" +
                "    \"job\": \"Tester\"\n" +
                "}";

        given()
                .baseUri("https://reqres.in/api/users/2")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .then().statusCode(200)
                .body(".name", equalTo("shikha"));

    }
@Test
public  void deleteUser(){

    validatableResponse = given()
            .baseUri("https://reqres.in/api/users/2")
            .contentType(ContentType.JSON)
            .when()
            .delete()
            .then()
            .assertThat().statusCode(204);

}

}




