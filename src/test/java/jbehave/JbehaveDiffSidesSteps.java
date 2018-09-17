package jbehave;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DiffResultSteps;
import steps.DiffSidesSteps;

public class JbehaveDiffSidesSteps {
    @Steps
    private DiffSidesSteps diffSidesSteps;

    @Steps
    private DiffResultSteps diffResultSteps;


    @Given("I set the left side of id $id to $diffString")
    public void setLeftSide(@Named("id") int id,
                            @Named("diffString") String diffString) {
        diffSidesSteps.setLeftDiffSideToString(id, diffString);
        diffSidesSteps.verifyLeftSideSuccess(diffString);
    }

    @Given("I set the right side of id $id to $diffString")
    public void setRightSide(@Named("id") int id,
                            @Named("diffString") String diffString) {
        diffSidesSteps.setRightDiffSideToString(id, diffString);
        diffSidesSteps.verifyRightSideSucess(diffString);
    }

    @When("I get the diff result for id $id")
    public void getDiffResult(@Named("id") int id) {
        diffResultSteps.getResultForDiffId(id);
    }

    @Then("I verify that the diff response is of type Equal")
    public void verifyDiffResponseIsEqual() {
        diffResultSteps.iVerifyThatDiffResultIsEqual();
    }
}
