import firstRestAssuredTest.config.EndPoint;
import firstRestAssuredTest.config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GPathJSONTests extends TestConfig {
    @Test // витягуємо окремого юзера з усіх юзерів
    public void extractMapOfElementsWithFind(){
        Response response = get("users?page=2");

        Map<String,?> allUsersData = response.path
                ("data.find {it.first_name == 'Byron'}");

        System.out.println(allUsersData);
    }

    @Test //витягумо ім'я користувач з певним id
    public void extractSingleValueWithFind(){
        Response response = get("users?page=2");
        String someUserb = response.path(("data.find{it.id == 8}.first_name"));
        System.out.println(someUserb);
    }

    @Test //витягуємо всіх користувачів в яких id більше 10
    public void extractListOfValue(){
        Response response = get("users?page=2");
        List<String> usersNames = response.path("data.findAll{ it.id > 10}.first_name");
        System.out.println(usersNames);
    }
    @Test
    public void extractSingleValueWithHifhesrNumber(){
        Response response = get("users?page=2");
        String userName = response.path("data.min {it.id}.first_name");
        System.out.println(userName);
    }

    @Test //рахуємо суму всіх ID зі сторінки
    public void extractMutipleValuesAndSumThem(){
        Response response = get("users?page=2");
        int sumOfId = response.path("data.collect {it.id}.sum()");
        System.out.println(sumOfId);
    }

    @Test
    public void extractMapOfObgectWithFindAndFindAll(){
        String lastName = "Lawson";
        String firstName = "Michael";
        Response response = get("users?page=2");
        Map<String, ?> usersOf = response.path("data.findAll {it.last_name == '%s'}.find{it.first_name == '%s'}",
                lastName, firstName);
        // Map<String, ?> usersOf = response.path(
      //          "data.findAll {it.last_name == \"Lawson\"}.find{it.first_name == \"Michael\" }");
        System.out.println(usersOf);
    }

    @Test
    public void extractMultiplePlayers(){
        String lastName = "Lawson";
        String firstName = "Michael";

        Response response = get("users?page=2");
        ArrayList<Map<String,?>> allPlayersFirstName = response.path(
                "data.findAll {it.last_name == '%s'}.findAll{it.first_name == '%s'}",
                lastName, firstName);
        System.out.println(allPlayersFirstName);
    }
}
