package constants;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static String baseurl="https://gorest.co.in/public/v2/";
    public static String userEndpoint="users";
    public static String postEndpoint="posts";
    public  static int random = (int) (Math.random()*500+1);
    static Map<String,String> headers=new HashMap<>();
    public static Map<String,String> headers(){

        headers.put("Content-Type","application/json; charset=utf-8"); //.contentType(ContentType.JSON)
        headers.put("Authorization","Bearer 43c383aadfb3f78ffcd9b9449b6b713106a6fa88cbc8a887ab89f21121449c86");
        return headers;
    }
}
