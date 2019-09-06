package firstRestAssuredTest;

import firstRestAssuredTest.config.EndPoint;
import firstRestAssuredTest.config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.CoreMatchers.equalTo;

public class UsersDBTest extends TestConfig implements EndPoint {
    @Test
    public void getAllUsers(){

        given()
                .when()
                .get(EndPoint.USERS)
                .then()
                .log()
                .all();
    }

    @Test
    public void createNewUserByJSON(){
        String userIraLarson = "{\"name\": \"ira\", \"job\": \"QA\"}";
        given()
                .body(userIraLarson)
                .when()
                .post(EndPoint.USERS)
                .then()
                .log()
                .all();


    }

    @Test
    public void updateUser(){
        String userIraLarson = "{\"name\": \"ira ira\", \"job\": \"QA\"}";
        given()
                .body(userIraLarson)
                .when()
                .put("/users/989")
                .then()
                .log()
                .all();
    }

    @Test
    public void deleteUser(){
        given()
                .when()
                .delete("/users/989")
                .then()
                .log()
                .all();
    }

    @Test
    public void getSingleUser(){
        given()
                .pathParam("userID", 2)
                .when()
                .get(EndPoint.SINGLE_USER)
                .then()
                .log()
                .all();

    }

    @Test
    public void getUsersCount_ForOnePage() {
        given()
                .spec(requestSpecification)
                .when()
                .get(EndPoint.USERS)
                .then()
                .spec(reqres_responceSpec)
                .and()
                .body("total", equalTo(12))
                .log()
                .all();
    }

    @Test
    public void checkFirstUserName(){
        given()
                // .pathParam("userID", 1)
                .when()
                .get(EndPoint.USERS)
                .then()
                .body("data.first_name[0]", equalTo("George"))
                .log()
                .all();
    }

    @Test
    public void getAllUsersDataa(){
        String responseBody = given().when().get(EndPoint.USERS).asString();
        System.out.println(responseBody);
    }

    @Test
    public  void getAllUsersData_DoCheckFirst(){
        Response response =
                given()
                        .when().
                        get(EndPoint.USERS)
                        .then()
                        .contentType(ContentType.JSON)
                        .extract().response();
        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void extractHeaders(){
        Response response =
                given()
                        .when()
                        .get(EndPoint.USERS)
                        .then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        //Headers headers = response.getHeaders();

        String contentType = response.getHeader("Content-Type");
        System.out.println(contentType);
    }

    @Test //витягуємо FirstName з JSON та роздруковуємо
    public void extractFirstUserName() {
        String firstTeamName =
                given()
                        .when().get(EndPoint.USERS).jsonPath().getString("data.first_name[0]");
        System.out.println(firstTeamName);
    }

    @Test // витягуємо усі імена користувачів з першої сторінки
    public void extractAllTeamNames(){
        Response response = given()
                .when().get(EndPoint.USERS)
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        List<String> teamNames = response.path("data.first_name");
        for(String teanName : teamNames){
            System.out.println(teanName);
        }

    }
}
