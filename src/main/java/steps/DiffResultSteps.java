package steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.is;

public class DiffResultSteps {

    private static final String SERVER_ADDRESS = "http://localhost:8081/diffassign/v1/diff/";
    private Response response;

    @Step("I get the diff result for id {0}")
    public void getResultForDiffId(int id) {
        response = SerenityRest.when().get(SERVER_ADDRESS + id);
        response.then().statusCode(200);
    }

    @Step("I verify the diff response is Equal")
    public void iVerifyThatDiffResultIsEqual() {
        response.then().body("type", is("EQUAL"));
    }
}
