import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

public class Commons {
    protected String POSTS = "/posts";
    protected String COMMENTS = "/comments";
    protected String USERS = "/users";
    protected FakeValuesService fakeValuesService;
    protected RequestSpecification requestSpecification;

    public Commons() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
    }

    public void assertBodyMatchesString(Response response,String entity) {

    }
}
