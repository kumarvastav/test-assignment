import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.InputStream;

import static org.hamcrest.Matchers.hasSize;

public class PostDefinitions extends Commons {
    Response response;
    public InputStream JSONSchema;

    @Given("the jsonPlaceholder api is available")
    public void the_jsonPlaceholder_api_is_available() {
        requestSpecification.given();
    }

    @Then("^response has \"([^\"]*)\" as \"([^\"]*)\"$")
    public void postTitleIsShown(String path,String value) {
        assertBodyMatches(response, path, value);
    }

    @Then("^response throws status code '(\\d+)'$")
    public void responseThrowsStatusCode(int code) {
        assertStatusCode(response,code);
    }

    @And("^response shows '(\\d+)' list items$")
    public void listOfAllPostsAreShown(int count) {
        response.then()
                .assertThat()
                .body("$", hasSize(count));
    }


    @When("^post is fetched with endpoint \"([^\"]*)\"$")
    public void postIsFetchedWithEndpoint(String route) {
        response = requestSpecification
                .get(route)
                .then()
                .extract().response();
    }

    @When("^post is created with userid \"([^\"]*)\",title \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void postIsCreatedWithUseridTitleAndBody(String userId, String title, String body) {
        try{
            String payload = helpers.createPostBody(userId,title,body);
            response = requestSpecification
                    .body(payload)
                    .post(POSTS)
                    .then()
                    .extract().response();
        }catch (JsonProcessingException e){System.out.println(e.getMessage());}
    }

    @When("^post is fetched with id \"([^\"]*)\" with endpoint \"([^\"]*)\"$")
    public void postIsFetchedWithIdWithEndpoint(String ID, String ROUTE) {
        response = requestSpecification
                .get(POSTS+"/"+ID+ROUTE)
                .then()
                .extract().response();
    }

    @When("^\"([^\"]*)\" endpoint is fetched with id '(\\d+)'$")
    public void endpointIsFetchedWithId(String endpoint, int args) {
        response = requestSpecification
                .get(endpoint +"/"+args)
                .then()
                .extract().response();
    }

    @When("^comment is fetched with endpoint \"([^\"]*)\"$")
    public void commentIsFetchedWithEndpoint(String route) {
        response = requestSpecification
                .get(route)
                .then()
                .extract().response();
    }

    @When("^comment is created with postid \"([^\"]*)\",name \"([^\"]*)\",email \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void commentIsCreatedWithPostIdNameEmailAndBody(String postId, String name, String email, String body) {
        try{
            String payload = helpers.createCommentBody(postId,name,email,body);
            response = requestSpecification
                    .body(payload)
                    .post(POSTS)
                    .then()
                    .extract().response();
        }catch (JsonProcessingException e){System.out.println(e.getMessage());}
    }
}
