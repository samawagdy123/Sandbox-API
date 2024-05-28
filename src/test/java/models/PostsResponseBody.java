package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class PostsResponseBody {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"id", "user_id", "title", "body"})

    @JsonProperty("id")
    public Integer postId;
    @JsonProperty("user_id")
    public String userId ;
    @JsonProperty("title")
    public String postTitle;
    @JsonProperty("body")
    public String postBody;

}
