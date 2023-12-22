import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasSize;

public class PostDefinitions extends Commons {
    Response response;

    @Given("the Post creation API is available")
    public void the_post_creation_api_is_available() {
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
    public void postTitleIsShown(String str1) {
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
}
