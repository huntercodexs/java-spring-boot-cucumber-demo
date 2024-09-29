//package com.huntercodexs.demo.pact.consumer;
//
//import au.com.dius.pact.consumer.dsl.DslPart;
//import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
//import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
//import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
//import au.com.dius.pact.consumer.junit5.PactTestFor;
//import au.com.dius.pact.core.model.PactSpecVersion;
//import au.com.dius.pact.core.model.RequestResponsePact;
//import au.com.dius.pact.core.model.annotations.Pact;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.huntercodexs.demo.config.ConstantsConfig.*;
//import static com.huntercodexs.demo.util.ResourceUtil.getMockRequest;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(PactConsumerTestExt.class)
//class GetUsersIntegrationTest {
//
//    Map<String, String> headers = new HashMap<>();
//
//    @Pact(provider = PACT_PROVIDER, consumer = PACT_CONSUMER)
//    public RequestResponsePact createPact(PactDslWithProvider builder) {
//
//        headers.put("Content-Type", "application/json");
//        headers.put("Accept", "application/json");
//
//        DslPart bodyReturned = PactDslJsonArray.arrayEachLike()
//                .stringType("name", USERS[0][1])
//                .stringType("username", USERS[0][2])
//                .stringType("password", USERS[0][3])
//                .stringType("email", USERS[0][4])
//                .uuid("id", USERS[0][0])
//                //.closeArray()
//                .closeObject();
//
//        return builder
//                .uponReceiving("Request to retrieve all users")
//                .path(URI_USERS)
//                .method("GET")
//                .headers(headers)
//                .willRespondWith()
//                .body(bodyReturned)
//                .toPact();
//
//    }
//
//    @Test
//    @PactTestFor(providerName = PACT_PROVIDER, port = PACT_PORT_MOCK, pactVersion = PactSpecVersion.V3)
//    void runTest() {
//        Response response = getMockRequest(headers).get(URI_USERS);
//        assertEquals(200, response.getStatusCode());
//    }
//
//}
