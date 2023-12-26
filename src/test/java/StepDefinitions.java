import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StepDefinitions extends Base {
    Response response;
    String postId;
    String payload;

    @Given("the jsonPlaceholder api is available")
    public void theJsonPlaceholderApiIsAvailable() {
        requestSpecification.given();
    }

    @Then("^response has \"([^\"]*)\" as \"([^\"]*)\"$")
    public void websitePostTitleIsShown(String param,String value) {
        assertBodyMatches(response, param, value);
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
            payload = getBodyPayload(helpers.createPostBody(userId,title,body));
            response = requestSpecification
                    .body(payload)
                    .post(POSTS)
                    .then()
                    .extract().response();
    }

    @When("^post is fetched with id \"([^\"]*)\" using endpoint \"([^\"]*)\"$")
    public void postIsFetchedWithIdUsingEndpoint(String postId, String route) {
        response = requestSpecification
                .get(POSTS+"/"+postId+route)
                .then()
                .extract().response();
    }

    @When("^\"([^\"]*)\" endpoint is fetched with id '(\\d+)'$")
    public void endpointIsFetchedWithId(String endpoint, int id) {
        response = requestSpecification
                .get(endpoint +"/"+ id)
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
            payload = getBodyPayload(helpers.createCommentBody(postId,name,email,body));
            response = requestSpecification
                    .body(payload)
                    .post(COMMENTS)
                    .then()
                    .extract().response();
    }

    @And("^a comment is created on the above post with name \"([^\"]*)\",email \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void aCommentIsCreatedOnTheAbovePostWithNameEmailAndBody(String name, String email, String body) {
        postId = response
                .body()
                .jsonPath().get("id").toString();

        commentIsCreatedWithPostIdNameEmailAndBody(postId,name,email,body);
    }

    @And("^post \"([^\"]*)\" is updated with userid \"([^\"]*)\", title \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void aPostUpdatedWithIdUseridTitleAndBody(String postId,String userId, String title, String body) {
        payload = getBodyPayload(helpers.createPostBody(userId,title,body));
        response = requestSpecification
                .body(payload)
                .put(POSTS+"/"+postId)
                .then()
                .extract().response();
    }

    @And("^view all the comments on the post \"([^\"]*)\"$")
    public void viewAllTheCommentsOnThePost(String postId) {
        response = requestSpecification
                .get(POSTS+"/"+postId+COMMENTS)
                .then()
                .extract().response();
    }

    @When("^post \"([^\"]*)\" is patched only with \"([^\"]*)\" as \"([^\"]*)\"$")
    public void postIsPatchedOnlyWithAsAndBody(String postId, String param, String value) {
        Map<String,String> body = new HashMap<>();
        body.put(param,value);

        payload = getBodyPayload(body);
        response = requestSpecification
                .body(payload)
                .patch(POSTS + "/" + postId)
                .then()
                .extract().response();
    }

    @When("^the post \"([^\"]*)\" is deleted$")
    public void thePostIsDeleted(String postId) {
        response = requestSpecification
                .delete(POSTS+"/"+postId)
                .then()
                .extract().response();
    }

    @When("^comment \"([^\"]*)\" is updated with postid \"([^\"]*)\",name \"([^\"]*)\",email \"([^\"]*)\" and body \"([^\"]*)\"$")
    public void commentIsUpdatedWithPostidNameEmailAndBody(String commentId, String postId, String name, String email, String body) {
        payload = getBodyPayload(helpers.createCommentBody(postId,name,email,body));

        response = requestSpecification
                .body(payload)
                .put(COMMENTS+"/"+commentId)
                .then()
                .extract().response();
    }

    @When("^comment \"([^\"]*)\" is patched only with \"([^\"]*)\" as \"([^\"]*)\"$")
    public void commentIsPatchedOnlyWithAs(String commentId, String param, String value) {
        Map<String,String> body = new HashMap<>();
        body.put(param,value);

        payload = getBodyPayload(body);

        response = requestSpecification
                .body(payload)
                .patch(COMMENTS + "/" + commentId)
                .then()
                .extract().response();
    }

    @When("^the comment \"([^\"]*)\" is deleted$")
    public void theCommentIsDeleted(String commentId) {
        response = requestSpecification
                .delete(COMMENTS+"/"+commentId)
                .then()
                .extract().response();
    }

    @When("^user is fetched with endpoint \"([^\"]*)\"$")
    public void userIsFetchedWithEndpoint(String route) {
        response = requestSpecification
                .get(route)
                .then()
                .extract().response();
    }

    @When("^a user is created with name \"([^\"]*)\", username \"([^\"]*)\", email \"([^\"]*)\", phone \"([^\"]*)\", website \"([^\"]*)\"$")
    public void aUserIsCreatedWithNameUsernameEmailPhoneWebsite(String name, String username, String email, String phone, String website) {
        payload = getBodyPayload(helpers.createUserBody(name, username, email, phone, website));
        response = requestSpecification
                .body(payload)
                .post(USERS)
                .then()
                .extract().response();
    }

    @When("^user \"([^\"]*)\" is updated with name \"([^\"]*)\", username \"([^\"]*)\", email \"([^\"]*)\", phone \"([^\"]*)\", website \"([^\"]*)\"$")
    public void userIsUpdatedWithNameUsernameEmailPhoneWebsite(String userId, String name, String username, String email, String phone, String website) {
        payload = getBodyPayload(helpers.createUserBody(name, username, email, phone, website));
        response = requestSpecification
                .body(payload)
                .put(USERS + "/" + userId)
                .then()
                .extract().response();
    }

    @When("^user \"([^\"]*)\" is patched with \"([^\"]*)\" as \"([^\"]*)\" and \"([^\"]*)\" as \"([^\"]*)\"$")
    public void userIsPatchedWithAsAndAs(String userId, String param1, String value1, String param2, String value2) {
        Map<String, String> body = new HashMap<>();
        body.put(param1, value1);
        body.put(param2, value2);

        payload = getBodyPayload(body);

        response = requestSpecification
                .body(payload)
                .patch(USERS + "/" + userId)
                .then()
                .extract().response();
    }

    @When("^the user \"([^\"]*)\" is deleted$")
    public void theUserIsDeleted(String userId) {
        response = requestSpecification
                .delete(USERS+"/"+userId)
                .then()
                .extract().response();
    }

    @When("^user is fetched with id \"([^\"]*)\" using endpoint \"([^\"]*)\"$")
    public void userIsFetchedWithIdUsingEndpoint(String userId, String route) {
        response = requestSpecification
                .get(USERS +"/"+userId+route)
                .then()
                .extract().response();
    }


    @And("^response shows all items with \"([^\"]*)\" as '(\\d+)'$")
    public void responseShowsAllItemsWithAs(String param, String value) {
        List<Integer> userIds = response.then()
                .extract().jsonPath().get(param);
        assertTrue(userIds.stream().anyMatch(owner -> !owner.equals(value)));
    }
}
