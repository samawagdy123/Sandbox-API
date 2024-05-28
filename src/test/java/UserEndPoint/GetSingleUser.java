package UserEndPoint;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Requests.UserRequests;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static constants.Constants.*;

public class GetSingleUser {
    JsonPath jsonpath;
    String ID="";

    @BeforeTest
    public void createSingleUser(){
        String name="sama";String email="sama"+random+"@gmail.com";String gender="female";String status="active";
        Response response= UserRequests.createUser(name,email,gender,status);
        jsonpath = response.jsonPath();
        ID = jsonpath.get("id").toString();
    }

    @Test
    public void getUserTest(){
        Response response= UserRequests.getUser(ID);
        response.prettyPrint();
        response.then().statusCode(200);
        jsonpath = response.jsonPath();
        SoftAssert soft =new SoftAssert();
        soft.assertEquals(jsonpath.get("id").toString(),ID,"not the same user");
        soft.assertAll();
    }

    @AfterTest
    public void deleteUserTest(){
        Response response= UserRequests.deleteUser(ID);
        response.prettyPrint();
//        response.then().statusCode(204);
    }

}
