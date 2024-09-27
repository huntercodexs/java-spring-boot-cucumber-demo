package com.huntercodexs.demo.steps;

import com.huntercodexs.demo.client.RestClient;
import com.huntercodexs.demo.model.UserModel;
import com.huntercodexs.demo.steps.context.UserContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.huntercodexs.demo.config.Constants.PASSWORD;
import static com.huntercodexs.demo.config.Constants.USERNAME;

@Data
@RequiredArgsConstructor
public class ApiSteps {

    private final RestClient restClient;

    private Response response;

    private final UserContext uc;

    @Given("I access the get users endpoint")
    public void iAccessGetUsersEndpoint() {
        RequestSpecification rq = restClient.getRequestSpecification()
                .auth().basic(USERNAME, PASSWORD);
        setResponse(rq.get("/api/v1/demo/users/"));
    }

    @Then("^I get a (\\d+) (successful|error) response$")
    public void iGetResponse(int responseCode, String responseType) {
        response.then().statusCode(responseCode);
    }

    @Then("The creation request is successful")
    public void iGetSuccessfulCreationResponse() {
        response.then().statusCode(201);
    }

    @Then("The creation request fails with a bad request error")
    public void iGetErrorForCreationRequest() {
        response.then().statusCode(400);
    }

    @Then("The retrieval request is successful")
    public void iGetSuccessfulRetrievalResponse() {
        response.then().statusCode(200);
    }

    @Then("^the system should block the user with a forbidden error")
    public void theUserIsBlockedWithForbiddenError() {
        response.then().statusCode(403);
    }

    @And("The response has all the expected fields for the get users endpoint")
    public void responseHasExpectedFieldsForGetUsersEndpoint() {
        getResponse().as(UserModel[].class);
    }

    public RequestSpecification getRqWithAuth() {
        return restClient.getRequestSpecification().auth().basic(uc.getUserDB().getUsername(), uc.getUserDB().getPassword());
    }

    public RequestSpecification getRqWithAuth(String username, String password) {
        return restClient.getRequestSpecification().auth().basic(username, password);
    }

}
