package firstRestAssuredTest.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.hasKey;

public class TestConfig {
    public static RequestSpecification reqres_requestSpec;
    public static RequestSpecification football_requestSpec;
    public static ResponseSpecification reqres_responceSpec;

    @BeforeClass
    public static void setup(){

        reqres_requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setBasePath("/api/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        RestAssured.requestSpecification = reqres_requestSpec;

        football_requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.football-data.org")
                .setBasePath("/v1/")
                .addHeader("X-Auto-Token", "fa765ac6db234544b157486ebecb3d6c")
                .addHeader("X-Response-Control","minified")
                .build();

        reqres_responceSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("$", hasKey("data"))
                .build();

        RestAssured.responseSpecification = reqres_responceSpec;

        //RestAssured.responseSpecification = reqres_requestSpec;
    }
}

