package steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.is;

public class DiffSidesSteps {
    private static final String SERVER_ADDRESS = "http://localhost:8081/diffassign/v1/diff/";
    private Response leftResponse;
    private Response rightResponse;

    @Step("I set the left side of diff id {0} to {1}")
    public void setLeftDiffSideToString(int id, String diffString) {
       leftResponse = setSideToString("left", id, diffString);
       leftResponse.then().statusCode(200);
    }

    @Step("I check that the left side was correctly set to {0}")
    public void verifyLeftSideSuccess(String leftString) {
        leftResponse.then().body("left", is(leftString));
    }

    @Step("I set the right side of diff id {0} to {1}")
    public void setRightDiffSideToString(int id, String diffString) {
        rightResponse = setSideToString("right", id, diffString);
        rightResponse.then().statusCode(200);
    }

    @Step("I check that the right side was correctly set to {0}")
    public void verifyRightSideSucess(String rightString) {
        leftResponse.then().body("left", is(rightString));
    }

    private Response setSideToString(String side, int id, String diffString) {
        return SerenityRest.given()
                .contentType("application/json")
                .content("\"" + diffString + "\"").log()
                .body()
                .baseUri(SERVER_ADDRESS)
                .basePath(id + "/" + side)
                .when().post();
    }

}
