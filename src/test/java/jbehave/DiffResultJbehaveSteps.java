package jbehave;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DiffResultSteps;

public class DiffResultJbehaveSteps {

    @Steps
    private DiffResultSteps diffResultSteps;

    @When("I get the diff result for id $id")
    public void getDiffResult(@Named("id") long id) {
        diffResultSteps.getResultForDiffId(id);
    }

    @When("I get the diff result for the new diff id")
    public void getNewDiffResult() {
        diffResultSteps.getResultForDiffId(Serenity.sessionVariableCalled("uniqueId"));
    }

    @Then("I verify that the diff response is of type EQUAL")
    public void verifyDiffResponseIsEqual() {
        diffResultSteps.verifyThatDiffResultIsEqual();
    }

    @Then("I verify that the diff response is left side with no value")
    public void verifyDiffResponseIsLeftWithNoValue() {
        diffResultSteps.verifyThatDiffResultIsNoValue("left");
    }

    @Then("I verify that the diff response is right side with no value")
    public void verifyDiffResponseIsRightWithNoValue() {
        diffResultSteps.verifyThatDiffResultIsNoValue("right");
    }
}
