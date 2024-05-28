package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class UsersResposeBody {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"id", "name", "email", "gender","status"})

    @JsonProperty("id")
    public String userId;
    @JsonProperty("name")
    public String userName;
    @JsonProperty("email")
    public String userEmail;
    @JsonProperty("gender")
    public String userGender;
    @JsonProperty("status")
    public String userStatus;

}







