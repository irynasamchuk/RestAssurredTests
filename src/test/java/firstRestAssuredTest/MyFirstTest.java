package firstRestAssuredTest;


import firstRestAssuredTest.config.EndPoint;
import firstRestAssuredTest.config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class MyFirstTest extends TestConfig implements EndPoint {
    @Test
    public  void myFirstTest(){
        given().
                log().
                all().
                when().get("users/2")
                .then()
                .log()
                .all();
    }

    @Test
    public void deleteUser(){
        given()
                .spec(reqres_requestSpec)
                .when()
                .delete("users/2")
                .then()
                .spec(reqres_responceSpec);

    }

    @Test
    public void getAllUsers(){
        given()
                .when()
                .get(EndPoint.USERS);
    }
}
