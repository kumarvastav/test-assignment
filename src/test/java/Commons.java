import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.*;

public class Commons {
    protected String POSTS = "/posts";
    protected String COMMENTS = "/comments";
    protected String USERS = "/users";
    protected ObjectMapper objectMapper;

    protected Helpers helpers;
    protected RequestSpecification requestSpecification;

    public Commons() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        helpers = new Helpers();
        objectMapper = new ObjectMapper();
    }

    public void assertBodyMatches(Response response,String path, String value) {
        response.then().extract().jsonPath()
                .get(path).equals(value);
    }

    public void assertStatusCode(Response response, int status) {
        response.then()
                .assertThat()
                .statusCode(status);
    }
}
