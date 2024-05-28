package UserEndPoint;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Requests.UserRequests;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import static constants.Constants.*;

public class UpdateUser {
    String id="";

    String name="doha";String email="sama"+random+"@gmail.com";String gender="female";String status="active";
    @BeforeClass
    public void createuser(){
        Response response= UserRequests.createUser(name,email,gender,status);
        JsonPath jsonPath = response.jsonPath();
        id = jsonPath.get("id").toString();
    }

    @Test
    public void updateSingleUser(){
        Response response;
        response= UserRequests.updateUser(id,"sama",email,gender,status);
        response.then().statusCode(200);

    }
    @Test
    public void checkStatusIncorrect(){
        Response response;
        response= UserRequests.updateUser(id,name,email,gender,"");
        response.then().statusCode(422);
        JsonPath jsonPath = response.jsonPath();
        SoftAssert soft=new SoftAssert();
        soft.assertEquals(jsonPath.get("["+0+"].field"),"status","not as expected");//second
        soft.assertTrue(jsonPath.getString("["+0+"].message").contains("can't be blank"),"should contain message");//third
        soft.assertAll();

    }

    @Test
    public void checkGenderIncorrect(){
        Response response;
        response= UserRequests.updateUser(id,name,email,"hola",status);
        response.then().statusCode(422);
        JsonPath jsonPath = response.jsonPath();
        SoftAssert soft=new SoftAssert();
        soft.assertEquals(jsonPath.get("["+0+"].field"),"gender","not as expected");//second
        soft.assertTrue(jsonPath.getString("["+0+"].message").contains("can't be blank"),"can't be blank");//third
        soft.assertAll();

    }


    @AfterClass
    public void deleteUserTest(){
        Response response= UserRequests.deleteUser(id);
        response.prettyPrint();
        response.then().statusCode(204);
    }
}
