package com.huntercodexs.demo.pact.consumer;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
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

import static com.huntercodexs.demo.util.Constants.*;
import static com.huntercodexs.demo.util.Utils4Test.getMockRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
class GetUsersIntegrationTest {

    Map<String, String> headers = new HashMap<>();

    String path = "/api/v1/demo/users/";

    @Pact(provider = PACT_PROVIDER, consumer = PACT_CONSUMER)
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        DslPart bodyReturned = PactDslJsonArray.arrayEachLike()
                .stringType("name", "anyName")
                .stringType("username", "anyUsername")
                .stringType("password", "anyPassword")
                .stringType("email", "anyEmail")
                .uuid("id", "e135b321-c58d-47c3-b9c4-c081a5b4684f")
                .closeArray()
                .closeObject();

        return builder
                .uponReceiving("A request to retrieve a list of users")
                .path(path)
                .method("GET")
                .headers(headers)
                .willRespondWith()
                .body(bodyReturned)
                .toPact();

    }

    @Test
    @PactTestFor(providerName = PACT_PROVIDER, port = MOCK_PACT_PORT, pactVersion = PactSpecVersion.V3)
    void runTest() {
        Response response = getMockRequest(headers).get(path);
        assertEquals(200, response.getStatusCode());
    }

}
