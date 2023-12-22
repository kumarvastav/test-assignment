import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.config.RequestConfig;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.hasSize;

public class PostDefinitions extends Commons {

    RequestConfig requestConfig;
    Response response;
    @Given("the Post creation API is available")
    public void the_post_creation_api_is_available() {
        // Write code here that turns the phrase above into concrete actions
        requestSpecification.given();
    }

    @When("^post is fetched with id '(\\d+)'$")
    public void postIsFetchedWithId(int id) {
        response = requestSpecification
                .get(POSTS+"/"+id)
                .then()
                .extract().response();
    }

    @Then("^post title \"([^\"]*)\" is shown$")
    public void postTitleIsShown(String str1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response.then()
                .assertThat()
                .body("title", Matchers.equalTo(str1));
    }

    @Then("^response throws status code '(\\d+)'$")
    public void responseThrowsStatusCode(int code) {
        response.then()
                .assertThat().statusCode(code);
    }

    @And("^list of all '(\\d+)' posts are shown$")
    public void listOfAllPostsAreShown(int count) {
        response.then()
                .assertThat()
                .body("$", hasSize(100));
    }


    @When("^post is fetched with endpoint \"([^\"]*)\"$")
    public void postIsFetchedWithEndpoint(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response = requestSpecification
                .get(POSTS)
                .then()
                .extract().response();
    }
}
