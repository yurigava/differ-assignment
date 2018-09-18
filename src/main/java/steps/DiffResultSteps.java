package steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.is;

public class DiffResultSteps {

    private static final String SERVER_ADDRESS = "http://localhost:8081/diffassign/v1/diff/";
    private Response response;

    @Step("I get the diff result for id {0}")
    public void getResultForDiffId(long id) {
        response = SerenityRest.when().get(SERVER_ADDRESS + id);
        response.then().statusCode(200);
    }

    @Step("I verify the diff response is {0}")
    public void verifyThatDiffResultIsType(String type) {
        response.then().body("type", is(type));
    }

    @Step("I verify the diff response detail is {0}")
    public void verifyThatDiffResultDetailIs(String message) {
        response.then().body("detail", is(message));
    }

    @Step("I verify the diff response is {0} side with no value")
    public void verifyThatDiffResultIsNoValue(String side) {
        response.then().body("type", is("DIFFERENT_LENGTH"));
        if("left".equals(side)) {
                    response.then().body("detail", is("Left side contains no value."));
        }
        else {
                    response.then().body("detail", is("Right side contains no value."));
        }
    }
}
