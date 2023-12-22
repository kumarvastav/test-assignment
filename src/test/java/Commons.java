import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Commons {
    protected String POSTS = "/posts";
    protected String COMMENTS = "/comments";
    protected String USERS = "/users";

    protected Helpers helpers;
    protected RequestSpecification requestSpecification;

    public Commons() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        helpers = new Helpers();
    }
}
