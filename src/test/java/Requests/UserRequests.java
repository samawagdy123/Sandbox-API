package Requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static constants.Constants.*;

public class UserRequests {
    public static Response createUser(String name, String email, String gender, String status){
        String requestBody = "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \""+gender+"\",\n" +
                "    \"status\": \""+status+"\"\n" +
                "}";
        Response response;
        response = RestAssured.given().headers(headers())
                .log().all()
                .body(requestBody)
                .post(baseurl + userEndpoint);
        return response;
    }

    public static Response getUser(String id){
        Response response;
        response= RestAssured.given()
                .log().all()
                .headers(headers())
                .get(baseurl+userEndpoint+"/"+id);
        return response;
    }

    public static Response deleteUser(String id){
        Response response;
        response= RestAssured.given()
                .log().all()
                .headers(headers())
                .delete(baseurl+userEndpoint+"/"+id);
        return response;
    }

    public static Response updateUser(String id,String name,String email,String gender,String status){
        Response response;
        String requestBody = "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"email\": \""+email +"\",\n" +
                "    \"gender\": \""+gender+"\",\n" +
                "    \"status\": \""+status+"\"\n" +
                "}";
        response= RestAssured.given()
                .log().all()
                .headers(headers()).body(requestBody)
                .put(baseurl+userEndpoint+"/"+id);
        return response;
    }
}
