package UserEndPoint;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Requests.UserRequests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static constants.Constants.random;

public class DeleteUser {

    String id="";

    String name="doha";String email="sama"+random+"@gmail.com";String gender="female";String status="active";
    @BeforeClass
    public void createuser(){
        Response response= UserRequests.createUser(name,email,gender,status);
        JsonPath jsonPath = response.jsonPath();
        id = jsonPath.get("id").toString();
    }

    @Test
    public void deleteSigleUser(){
        Response response= UserRequests.deleteUser(id);
        response.prettyPrint();
        response.then().statusCode(204);
    }

    @Test
    public void deleteUserNotExist(){
        Response response= UserRequests.deleteUser("2000");
        response.prettyPrint();
        response.then().statusCode(404);
        JsonPath jsonPath = response.jsonPath();
        SoftAssert soft=new SoftAssert();
        soft.assertTrue(jsonPath.getString("message").contains("Not Found"),"not as expected");//second
        soft.assertAll();

    }
}
