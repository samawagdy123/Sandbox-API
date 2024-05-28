package UserEndPoint;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Requests.UserRequests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static constants.Constants.*;


public class CreateUserTest {


    String ID="";
    JsonPath jsonpath;

    @Test
    public void createUserTest() {
        String name="sama";String email="sama"+random+"@gmail.com";String gender="female";String status="active";

        Response response= UserRequests.createUser(name,email,gender,status);
        jsonpath = response.jsonPath();
        response.prettyPrint();
        ID = jsonpath.get("id").toString();

        response.then().statusCode(201);//first
        SoftAssert soft=new SoftAssert();
        soft.assertEquals(jsonpath.getString("name"),name,"not as expected");//second
        soft.assertNotNull(jsonpath.get("id"),"id is created success");//third
        soft.assertAll("all test are done");
    }

    @Test
    public void allNotblank(){
        Response response= UserRequests.createUser("","","","");
        jsonpath = response.jsonPath();

        response.prettyPrint();
        response.then().statusCode(422);//first
        String[] arr={"email","name","gender","status"};

        SoftAssert soft=new SoftAssert();
        for (int i=0;i<4;i++){
            soft.assertEquals(jsonpath.get("["+i+"].field"),arr[i],"not name field");//second
            soft.assertNotNull(jsonpath.get("["+i+"].message"),"can't be blank");//third
        }
        soft.assertAll("all test are done");
    }
    @AfterClass
    public void deleteUserTest(){
        Response response= UserRequests.deleteUser(ID);
        response.prettyPrint();
        response.then().statusCode(204);
    }

}
