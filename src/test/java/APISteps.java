import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class APISteps {
        @Test
        public void testGetUsers(){
        baseURI="https://reqres.in/api";

        String body = given()
                    .when()
                        .get("/users")
                    .then()
                        .statusCode(200)
                        .body("data[1].first_name", equalTo("Janet"))
                        .extract().body().asString();

        System.out.println(body);


        /*
        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data[1].first_name", equalTo("Janet"));

            UserObject newUser = new UserObject();
        newUser.setName("lokesh");
        newUser.setEmail("admin@howtodoinjava.com");
        newUser.setGender("male");
        newUser.setStatus("active");

        String newUserXml = new XmlMapper().writeValueAsString(newUser);

        given()
                .body(newUserXml)
                .contentType("application/xml")
                .queryParam("access-token", "xxxx")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("lokesh"))
                .body("gender", equalTo("male"))
                .body("status", equalTo("active"))
                .body("email", equalTo("admin@howtodoinjava.com"))
                .log().all();
        */
    }

    @Test
    public void testPostUser(){
        baseURI="https://reqres.in/api";

        Map<String, Object> map = new HashMap<String,Object>();

        map.put("name","Jose");
        map.put("job","QA Analyst");

        given()
                .log().all()
                .body(map.toString())
        .when()
                .post("/users")
        .then()
                .log().all()
                .statusCode(201);
    }
}
