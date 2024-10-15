package codexstester.bdd.stepsdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BackgroundAPISteps {

    @Given("users endpoint is accessed using get")
    public void usersEndpointIsAccessedUsingGet() {
        System.out.println("=======> BackgroundAPISteps");
        System.out.println("=======> GET DATA FROM API");
    }

    @Then("^the result is (\\d+) (successful|error) http response$")
    public void theResultIsSuccessfulOrErrorFromHttpResponse(int responseCode, String responseType) {
        System.out.println("======> " + responseCode);
        System.out.println("======> " + responseType);
    }

    @Then("The creation request is successful")
    public void theCreationRequestIsSuccessful() {
        System.out.println("======> REQUEST STATUS TEST - SUCCESSFUL");
    }

    @Then("The creation request fails with a bad request error")
    public void theCreationRequestFailsWithBadRequestError() {
        System.out.println("======> REQUEST STATUS TEST - BAD REQUEST");
    }

    @Then("The retrieval request is successful")
    public void theRetrievalRequestIsSuccessful() {
        System.out.println("======> RESPONSE STATUS TEST - SUCCESSFUL");
    }

    @Then("^the system should block the user with a forbidden error")
    public void theSystemShouldBlockTheUserWithForbiddenError() {
        System.out.println("======> RESPONSE STATUS TEST - FORBIDDEN");
    }

    @And("the http response has all expected fields for get request")
    public void theHttpResponseHasAllExpectedFieldsForGetRequest() {
        System.out.println("======> GET ALL DATA");
    }

}
