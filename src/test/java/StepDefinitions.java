import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;

public class StepDefinitions extends Commons {
    Response response;
    String postId;

    @Given("the jsonPlaceholder api is available")
    public void the_jsonPlaceholder_api_is_available() {
        requestSpecification.given();
    }

    @Then("^response has \"([^\"]*)\" as \"([^\"]*)\"$")
    public void postTitleIsShown(String path,String value) {
        System.out.println(response.print());
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

    @When("^a post is created with userid \"([^\"]*)\",title \"([^\"]*)\" and body \"([^\"]*)\"$")
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
                    .post(COMMENTS)
                    .then()
                    .extract().response();
        }catch (JsonProcessingException e){System.out.println(e.getMessage());}
    }

    @And("^a comment is created on the above post with name \"([^\"]*)\",email \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void aCommentIsCreatedOnTheAbovePostWithNameEmailAndBody(String name, String email, String body) throws Throwable {
        postId = response
                .body()
                .jsonPath().get("id").toString();

        commentIsCreatedWithPostIdNameEmailAndBody(postId,name,email,body);
    }

    @And("^post \"([^\"]*)\" is updated with userid \"([^\"]*)\", title \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void aPostUpdatedWithIdUseridTitleAndBody(String postId,String userId, String title, String body) throws Throwable {
        String payload = helpers.createPostBody(userId,title,body);
        response = requestSpecification
                .body(payload)
                .put(POSTS+"/"+postId)
                .then()
                .extract().response();
    }

    @And("^view all the comments on the post \"([^\"]*)\"$")
    public void viewAllTheCommentsOnThePost(String postId) throws Throwable {
        response = requestSpecification
                .get(POSTS+"/"+postId+COMMENTS)
                .then()
                .extract().response();
    }

    @When("^post \"([^\"]*)\" is patched only with \"([^\"]*)\" as \"([^\"]*)\"$")
    public void postIsPatchedOnlyWithAsAndBody(String postId, String param, String value) throws Throwable {
        Map<String,String> payload = new HashMap<>();
        payload.put(param,value);

        response = requestSpecification
                .body(objectMapper.writeValueAsString(payload))
                .patch(POSTS+"/"+postId)
                .then()
                .extract().response();
    }

    @When("^the post \"([^\"]*)\" is deleted$")
    public void thePostIsDeleted(String postId) throws Throwable {
        response = requestSpecification
                .delete(POSTS+"/"+postId)
                .then()
                .extract().response();
    }

    @When("^comment \"([^\"]*)\" is updated with postid \"([^\"]*)\",name \"([^\"]*)\",email \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void commentIsUpdatedWithPostidNameEmailAndBody(String commentId, String postId, String name, String email, String body) throws Throwable {
        String payload = helpers.createCommentBody(postId,name,email,body);
        response = requestSpecification
                .body(payload)
                .put(COMMENTS+"/"+commentId)
                .then()
                .extract().response();
    }

    @When("^comment \"([^\"]*)\" is patched only with \"([^\"]*)\" as \"([^\"]*)\"$")
    public void commentIsPatchedOnlyWithAs(String commentId, String param, String value) throws Throwable {
        Map<String,String> payload = new HashMap<>();
        payload.put(param,value);

        response = requestSpecification
                .body(objectMapper.writeValueAsString(payload))
                .patch(COMMENTS+"/"+commentId)
                .then()
                .extract().response();
    }

    @When("^the comment \"([^\"]*)\" is deleted$")
    public void theCommentIsDeleted(String commentId) throws Throwable {
        response = requestSpecification
                .delete(COMMENTS+"/"+commentId)
                .then()
                .extract().response();
    }

    @When("^user is fetched with endpoint \"([^\"]*)\"$")
    public void userIsFetchedWithEndpoint(String route) throws Throwable {
        response = requestSpecification
                .get(route)
                .then()
                .extract().response();
    }
}
