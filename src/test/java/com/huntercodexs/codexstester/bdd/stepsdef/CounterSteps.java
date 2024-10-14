package com.huntercodexs.codexstester.bdd.stepsdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CounterSteps {

    @LocalServerPort
    String port;

    RequestEntity<String> request;
    ResponseEntity<String> response;

    @Given("the clients needs to start one specific job")
    public void theClientsNeedsToStartOneSpecificJob() {
        request = null;
    }

    @When("the client calls endpoint {string} passing argument value {string}")
    public void theClientCallsEndpointPassingValue(String endpoint, String value) {
        endpoint = endpoint
                .replaceFirst("^/", "")
                .replaceFirst("/$", "");
        response = new RestTemplate().exchange(
                "http://localhost:"+port+"/"+endpoint+"/"+value,
                HttpMethod.GET,
                request,
                String.class);
    }

    @Then("response status code is {int}")
    public void responseStatusCodeIs(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCodeValue());
    }

    @And("return string should be {string}")
    public void returnStringShouldBe(String expected) {
        Assert.assertEquals(expected, response.getBody());
    }


}
