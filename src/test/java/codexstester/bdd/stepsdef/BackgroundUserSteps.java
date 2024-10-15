package codexstester.bdd.stepsdef;

import cucumber.api.java.en.Then;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BackgroundUserSteps {

    @Then("the response is validated again from get users endpoint request")
    public void theResponseIsValidatedAgainFromGetUsersEndpointRequest() {
        System.out.println("=======> BackgroundUsersSteps");
        System.out.println("=======> FINAL VALIDATION");
    }
}
