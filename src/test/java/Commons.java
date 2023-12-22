import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class Commons {
    protected String POSTS = "/posts";

    protected Helpers helpers;
    protected RequestSpecification requestSpecification;

    public Commons() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        helpers = new Helpers();
    }

    public void assertBodyMatches(Response response,String path, String value) {
        response.then()
                .assertThat()
                .body(path, Matchers.equalTo(value));
    }

    public void assertStatusCode(Response response, int status) {
        response.then()
                .assertThat()
                .statusCode(status);
    }
}
