package com.huntercodexs.demo.pact.consumer;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.huntercodexs.demo.util.Constants.*;
import static com.huntercodexs.demo.util.Utils4Test.getMockRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
class GetUserIntegrationTest {

    Map<String, String> headers = new HashMap<>();

    String path = "/api/v1/demo/users/";
    UUID userId = UUID.fromString("1bfff94a-b70e-4b39-bd2a-be1c0f898589");

    @Pact(provider = PACT_PROVIDER, consumer = PACT_CONSUMER)
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        DslPart bodyReturned = new PactDslJsonBody()
                .uuid("id", userId)
                .stringType("name", "anyName")
                .stringType("username", "anyUsername")
                .stringType("password", "anyPassword")
                .stringType("email", "anyEmail")
                .uuid("id", "e135b321-c58d-47c3-b9c4-c081a5b4684f")
                .closeArray()
                .close();

        return builder
                .given("A request to retrieve a user")
                .uponReceiving("A request to retrieve a user")
                .pathFromProviderState(path + "${userId}", path + userId)
                .method("GET")
                .headers(headers)
                .willRespondWith()
                .status(200)
                .body(bodyReturned)
                .toPact();

    }

    @Test
    @PactTestFor(providerName = PACT_PROVIDER, port = MOCK_PACT_PORT, pactVersion = PactSpecVersion.V3)
    void runTest() {

        Response response = getMockRequest(headers).get(path + userId);
        assertEquals(200, response.getStatusCode());
    }

}
