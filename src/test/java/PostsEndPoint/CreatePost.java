package PostsEndPoint;

import Requests.PostRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import models.PostsResponseBody;
import models.UsersResposeBody;
import Requests.UserRequests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static constants.Constants.*;

public class CreatePost {

    ObjectMapper mapper;
    UsersResposeBody createUser;
    PostsResponseBody createPost=new PostsResponseBody();
    @BeforeTest
    public void createUser() throws JsonProcessingException {
        String name="sama";String email="sama"+random+"@gmail.com";String gender="female";String status="active";
        Response response= UserRequests.createUser(name,email,gender,status);
        String jsonResponse=response.getBody().asString();
        mapper=new ObjectMapper();
        createUser=mapper.readValue(jsonResponse, UsersResposeBody.class);
    }


    @Test
    public void createPost(){
         createPost.postTitle="hi";
         createPost.postBody="welcome";
      Response response= PostRequests.createPost(createUser,createPost);
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void createPostWithoutTitle(){
        createPost.postTitle="";
        createPost.postBody="welcome";
        Response response= PostRequests.createPost(createUser,createPost);
        response.prettyPrint();
        response.then().statusCode(422);
        SoftAssert soft =new SoftAssert();
        soft.assertEquals(createPost.postTitle, "","title shouldn't be blank");
        soft.assertAll();
    }

    @Test
    public void createPostWithoutBody(){
        createPost.postTitle="Hi";
        createPost.postBody="";
        Response response= PostRequests.createPost(createUser,createPost);
        response.prettyPrint();
        response.then().statusCode(422);
        SoftAssert soft =new SoftAssert();
        soft.assertEquals(createPost.postBody, "","body shouldn't be blank");
        soft.assertAll();
    }

    @Test
    public void createPostWithNonCreatedUser(){
        createPost.postTitle="Hi";
        createPost.postBody="welcome";
        UsersResposeBody createFakeUser=new UsersResposeBody();
        createFakeUser.userId="2";
        Response response= PostRequests.createPost(createFakeUser,createPost);
        response.prettyPrint();
        response.then().statusCode(422);
        SoftAssert soft =new SoftAssert();
        soft.assertEquals(createFakeUser.userId,"2" ,"this user must be forbidden");
        soft.assertAll();
    }

    @Test
    public void createPostWithStringUserId(){
        createPost.postTitle="Hi";
        createPost.postBody="welcome";
        UsersResposeBody createFakeUser=new UsersResposeBody();
        createFakeUser.userId="sss";
        Response response= PostRequests.createPost(createFakeUser,createPost);
        response.prettyPrint();
        response.then().statusCode(422);
        SoftAssert soft =new SoftAssert();
        soft.assertEquals(createFakeUser.userId,"sss" ,"user id isn't number");
        soft.assertAll();
    }


    @AfterClass
    public void deleteUserTest(){
        UserRequests.deleteUser(createUser.userId);
    }



}
