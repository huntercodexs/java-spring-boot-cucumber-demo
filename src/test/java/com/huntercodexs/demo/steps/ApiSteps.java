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

    @Given("users endpoint is accessed using get")
    public void usersEndpointIsAccessedUsingGet() {
        RequestSpecification rq = restClient
                .getRequestSpecification()
                .auth()
                .basic(USERS[0][2], USERS[0][3]);

        setResponse(rq.get(URI_USERS));
    }

    @Then("^the result is (\\d+) (successful|error) http response$")
    public void theResultIsSuccessfulOrErrorFromHttpResponse(int responseCode, String responseType) {
        response.then().statusCode(responseCode);
    }

    @Then("The creation request is successful")
    public void theCreationRequestIsSuccessful() {
        response.then().statusCode(201);
    }

    @Then("The creation request fails with a bad request error")
    public void theCreationRequestFailsWithBadRequestError() {
        response.then().statusCode(400);
    }

    @Then("The retrieval request is successful")
    public void theRetrievalRequestIsSuccessful() {
        response.then().statusCode(200);
    }

    @Then("^the system should block the user with a forbidden error")
    public void theSystemShouldBlockTheUserWithForbiddenError() {
        response.then().statusCode(403);
    }

    @And("the http response has all expected fields for get request")
    public void theHttpResponseHasAllExpectedFieldsForGetRequest() {
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
