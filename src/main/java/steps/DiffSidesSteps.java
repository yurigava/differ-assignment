package steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.is;

public class DiffSidesSteps {
    private static final String SERVER_ADDRESS = "http://localhost:8081/diffassign/v1/diff/";
    private Response leftResponse;
    private Response rightResponse;

    @Step("I set the {0} side of diff id {1} to {2}")
    public void setDiffSideToString(String side, long id, String diffString) {
        if("left".equals(side))
            leftResponse = setSideToString("left", id, "\"" + diffString + "\"");
        else
            rightResponse = setSideToString("right", id, "\"" + diffString + "\"");
    }

    @Step("I set the {0} side of diff {1} to a non JSON format String")
    public void setDiffSideToNonJSONString(String side, long id, String nonJson) {
        if("left".equals(side))
            leftResponse = setSideToString("left", id, nonJson);
        else
            rightResponse = setSideToString("right", id, nonJson);
    }

    @Step("I check that the {0} side was correctly set to {1}")
    public void verifySideSuccess(String side, String leftString) {
        if("left".equals(side))
            leftResponse.then().statusCode(200)
                    .and().body(side, is(leftString));
        else
            rightResponse.then().statusCode(200)
                    .and().body(side, is(leftString));
    }

    @Step("I verify the {0} error message is non JSON Format")
    public void verifyErrorIsNonJSON(String side) {
        Response responseToBeChecked;
        if("left".equals(side))
            responseToBeChecked = leftResponse;
        else
            responseToBeChecked = rightResponse;
        verifyErrorMessage(responseToBeChecked, 415, "Value in request body must be in JSON format.");
    }

    @Step("I verify the {0} error message is non Base64 Format")
    public void verifyErrorIsNonBase64(String side) {
        Response responseToBeChecked;
        if("left".equals(side))
            responseToBeChecked = leftResponse;
        else
            responseToBeChecked = rightResponse;
        verifyErrorMessage(responseToBeChecked, 415, "Data in body not Base64 formatted.");
    }

    private void verifyErrorMessage(Response response, int code, String message) {
        response.then().statusCode(code)
                .and().body("errorCode", is(code))
                .and().body("errorMessage", is(message));
    }

    private Response setSideToString(String side, long id, String diffString) {
        return SerenityRest.given()
                .contentType("application/json")
                .content(diffString).log()
                .body()
                .baseUri(SERVER_ADDRESS)
                .basePath(id + "/" + side)
                .when().post();
    }

}
