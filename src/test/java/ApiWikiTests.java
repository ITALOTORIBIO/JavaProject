import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ApiWikiTests {

    @Test
    public void testPostNewUser(){
        baseURI="https://todo.ly/api";

        String requestBody =
                "<UserObject>\r\n" +
                    "<Email>italo_alexander_441@hotmail.com</Email>\r\n" +
                    "<FullName>Italo Alexander</FullName>\r\n" +
                    "<Password>12345678</Password>\r\n" +
                "</UserObject>";

        //Inicio del Test
        given()
                .contentType("application/xml")
                .body(requestBody)
        .when()
                .post("/user.xml")
        .then()
                .log().all()
                .statusCode(200);

    }

    //PREGUNTA 1 - CORREO YA EXISTENTE
    @Test
    public void testPostUserWithExistingMail(){
        baseURI="https://todo.ly/api";

        String requestBody =
                            "<UserObject>\r\n" +
                                    "<Email>italo_alexander_441@hotmail.com</Email>\r\n" +
                                    "<FullName>Italo Alexander</FullName>\r\n" +
                                    "<Password>12345678</Password>\r\n" +
                            "</UserObject>";

        //Inicio del Test
        given()
                .contentType("application/xml")
                .body(requestBody)
        .when()
                .post("/user.xml")
        .then()
                .log().all()
                .body("APIError.ErrorMessage", equalTo("Account with this email address already exists"))
                .statusCode(400);

    }

    //PREGUNTA 2 - CORREO MAL FORMATO
    @Test
    public void testPostUserWithWrongFormatEmail(){
        baseURI="https://todo.ly/api";

        String requestBody =
                            "<UserObject>\r\n" +
                                "<Email>italo_alexander_441-hotmail.com</Email>\r\n" +
                                "<FullName>Italo Alexander</FullName>\r\n" +
                                "<Password>12345678</Password>\r\n" +
                            "</UserObject>";

        //Inicio del Test
        given()
                .contentType("application/xml")
                .body(requestBody)
        .when()
                .post("/user.xml")
        .then()
                .log().all()
                .body("APIError.ErrorMessage", equalTo("Invalid Email Address"))
                .statusCode(400);
    }

    //PREGUNTA 3 - VALIDAR ESTRUCTURA DEL RESPONSE
    @Test
    public void testPostUserEstructure(){
        baseURI="https://todo.ly/api";

        String requestBody =
                            "<UserObject>\r\n" +
                                    "<Email>italo_leex3ader_441@mail.com</Email>\r\n" +
                                    "<FullName>Italo Alexander</FullName>\r\n" +
                                    "<Password>12345678</Password>\r\n" +
                            "</UserObject>";

        //Inicio del Test
        given()
                .contentType("application/xml")
                .body(requestBody)
        .when()
                .post("/user.xml")
        .then()
                .log().all()
                .contentType(ContentType.XML)
                .body(matchesXsdInClasspath("users.xsd"))
                .statusCode(201);
    }

    //PREGUNTA 4 - VALIDAR ENVIO VACIO DE EMAIL LOGIN
    @Test
    public void testLoginEmptyEmail(){
        baseURI="https://api-nodejs-todolist.herokuapp.com";

        String requestBody =
                            "<UserObject>\r\n" +
                                    "<Email></Email>\r\n" +
                                    "<Password>12345678</Password>\r\n" +
                            "</UserObject>";

        //Inicio del Test
        given()
                .contentType("application/xml")
                .body(requestBody)
        .when()
                .post("/user/login")
        .then()
                .log().all()
                .contentType(ContentType.JSON)
                .body("APIError.ErrorMessage", equalTo("Invalid Email Address"))
                .statusCode(400);
    }

    //PREGUNTA 5 - VALIDAR ENVIO VACIO DE PASSWORD LOGIN
    @Test
    public void testLoginEmptyPassword(){
        baseURI="https://api-nodejs-todolist.herokuapp.com";

        String requestBody =
                            "<UserObject>\r\n" +
                                    "<Email>italo_leex3ader_441@mail.com</Email>\r\n" +
                                    "<Password></Password>\r\n" +
                            "</UserObject>";

        //Inicio del Test
        given()
                .contentType("application/xml")
                .body(requestBody)
        .when()
                .post("/user/login")
        .then()
                .log().all()
                .contentType(ContentType.XML)
                .body("APIError.ErrorMessage", equalTo("Empty Password"))
                .statusCode(400);
    }

    //PREGUNTA 6 - VALIDAR ENVIO LOGIN CORRECTO
    @Test
    public void testLoginSuccesful(){
        baseURI="https://api-nodejs-todolist.herokuapp.com";

        String requestBody =
                            "<UserObject>\r\n" +
                                    "<Email>italo_leex3ader_441@mail.com</Email>\r\n" +
                                    "<Password>12345678</Password>\r\n" +
                            "</UserObject>";

        //Inicio del Test
        given()
                .contentType("application/xml")
                .body(requestBody)
        .when()
                .post("/user/login")
        .then()
                .log().all()
                .contentType(ContentType.XML)
                .body(matchesXsdInClasspath("users.xsd"))
                .statusCode(201);
    }

    /*//PREGUNTA 7 - VALIDAR EL LOGOUT WITHOUT TOKEN
    @Test
    public void testLogoutWithoutToken(){
        baseURI="https://api-nodejs-todolist.herokuapp.com";
        File requestFile= new File("")

        //Inicio del Test
        given()
        .when()
                .post("/user/logout")
        .then()
                .log().all()
    }*/
}


