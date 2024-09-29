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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.huntercodexs.demo.config.ConstantsConfig.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class ApiSteps {

    private Response response;
    private final RestClient restClient;
    private final UserContext userContext;

    @Given("I access the get users endpoint")
    public void iAccessGetUsersEndpoint() {
        RequestSpecification rq = restClient
                .getRequestSpecification()
                .auth()
                .basic(USERS[0][1], USERS[0][1]);

        setResponse(rq.get(URI_USERS));
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
        return restClient.getRequestSpecification()
                .auth()
                .basic(
                        userContext.getFirstUserDB().getUsername(),
                        userContext.getFirstUserDB().getPassword());
    }

    public RequestSpecification getRqWithAuth(String username, String password) {
        return restClient.getRequestSpecification()
                .auth()
                .basic(username, password);
    }

}
