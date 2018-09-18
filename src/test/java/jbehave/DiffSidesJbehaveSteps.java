package jbehave;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DiffResultSteps;
import steps.DiffSidesSteps;

public class DiffSidesJbehaveSteps {
    @Steps
    private DiffSidesSteps diffSidesSteps;

    @Given("I successfully set the left side of id $id to '$diffString'")
    public void successfullySetLeftSide(@Named("id") long id,
                            @Named("diffString") String diffString) {
        diffSidesSteps.setDiffSideToString("left", id, diffString);
        diffSidesSteps.verifySideSuccess("left", diffString);
    }

    @Given("I successfully set the right side of id $id to '$diffString'")
    public void successfullySetRightSide(@Named("id") long id,
                            @Named("diffString") String diffString) {
        diffSidesSteps.setDiffSideToString("right", id, diffString);
        diffSidesSteps.verifySideSuccess("right", diffString);
    }

    @Given("I successfully set the right side of a new diff id to '$diffString'")
    public void successfullySetRightSideOfNewId(@Named("diffString") String diffString) {
        long newId = System.nanoTime();
        Serenity.setSessionVariable("uniqueId").to(newId);
        successfullySetRightSide(newId, diffString);
    }

    @Given("I successfully set the left side of a new diff id to '$diffString'")
    public void successfullySetLeftSideOfNewId(@Named("diffString") String diffString) {
        long newId = System.nanoTime();
        Serenity.setSessionVariable("uniqueId").to(newId);
        successfullySetLeftSide(newId, diffString);
    }

    @When("I set the left side to non JSON string '$nonJsonString' on id $id")
    public void setLeftSideToNonJSON(@Named("nonJsonString") String nonJsonString,
                                     @Named("id") long id) {
        diffSidesSteps.setDiffSideToNonJSONString("left", id, nonJsonString);
    }

    @When("I set the right side to non JSON string '$nonJsonString' on id $id")
    public void setRightSideToNonJSON(@Named("nonJsonString") String nonJsonString,
                                     @Named("id") long id) {
        diffSidesSteps.setDiffSideToNonJSONString("right", id, nonJsonString);
    }

    @When("I set the left side of id $id to '$diffString'")
    public void setLeftSide(@Named("id") long id,
                            @Named("diffString") String diffString) {
        diffSidesSteps.setDiffSideToString("left", id, diffString);
    }

    @When("I set the right side of id $id to '$diffString'")
    public void setRightSide(@Named("id") int id,
                            @Named("diffString") String diffString) {
        diffSidesSteps.setDiffSideToString("right", id, diffString);
    }

    @Then("I verify the left response error is Non JSON Format")
    public void verifyLeftErrorIsNonJSON() {
        diffSidesSteps.verifyErrorIsNonJSON("left");
    }

    @Then("I verify the right response error is Non JSON Format")
    public void verifyRightErrorIsNonJSON() {
        diffSidesSteps.verifyErrorIsNonJSON("right");
    }

    @Then("I verify the left response error is non Base64 Format")
    public void verifyLeftErrorIsNonBase64() {
        diffSidesSteps.verifyErrorIsNonBase64("left");
    }

    @Then("I verify the right response error is non Base64 Format")
    public void verifyRightErrorIsNonBase64() {
        diffSidesSteps.verifyErrorIsNonBase64("right");
    }
}
