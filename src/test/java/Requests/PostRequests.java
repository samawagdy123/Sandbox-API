package Requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.PostsResponseBody;
import models.UsersResposeBody;

import static constants.Constants.*;

public class PostRequests {
    public static Response createPost(UsersResposeBody createUser, PostsResponseBody createPost){
        createPost.userId=createUser.userId;
        String requestBody="{\n" +
                "    \"user_id\":"+createPost.userId+",\n" +
                "    \"title\": \""+createPost.postTitle+"\",\n" +
                "    \"body\": \""+createPost.postBody+"\"\n" +
                "}";

        Response response = RestAssured.given().headers(headers())
                .log().all()
                .body(requestBody)
                .post(baseurl+postEndpoint);
        return response;
    }
}
