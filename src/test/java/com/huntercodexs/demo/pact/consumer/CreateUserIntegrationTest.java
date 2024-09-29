//package com.huntercodexs.demo.pact.consumer;
//
//import au.com.dius.pact.consumer.dsl.DslPart;
//import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
//import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
//import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
//import au.com.dius.pact.consumer.junit5.PactTestFor;
//import au.com.dius.pact.core.model.PactSpecVersion;
//import au.com.dius.pact.core.model.RequestResponsePact;
//import au.com.dius.pact.core.model.annotations.Pact;
//import com.huntercodexs.demo.model.UserModel;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import static com.huntercodexs.demo.config.ConstantsConfig.*;
//import static com.huntercodexs.demo.util.ResourceUtil.getMockRequest;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(PactConsumerTestExt.class)
//class CreateUserIntegrationTest {
//
//    Map<String, String> headers = new HashMap<>();
//
//    @Pact(provider = PACT_PROVIDER, consumer = PACT_CONSUMER)
//    public RequestResponsePact createPact(PactDslWithProvider builder) {
//
//        headers.put("Content-Type", "application/json");
//        headers.put("Accept", "application/json");
//
//        DslPart bodyReceived = new PactDslJsonBody()
//                .stringType("name", USERS[0][1])
//                .stringType("username", USERS[0][2])
//                .stringType("password", USERS[0][3])
//                .stringType("email", USERS[0][4])
//                .uuid("id", USERS[0][0])
//                //.closeArray()
//                .close();
//
//        DslPart bodyReturned = new PactDslJsonBody()
//                .stringType("name", USERS[0][1])
//                .stringType("username", USERS[0][2])
//                .stringType("password", USERS[0][3])
//                .stringType("email", USERS[0][4])
//                .uuid("id", USERS[0][0])
//                //.closeArray()
//                .close();
//
//        return builder
//                .uponReceiving("Request to user create")
//                .path(URI_USERS)
//                .body(bodyReceived)
//                .method("POST")
//                .headers(headers)
//                .willRespondWith()
//                .status(201)
//                .body(bodyReturned)
//                .toPact();
//    }
//
//    @Test
//    @PactTestFor(providerName = PACT_PROVIDER, port = PACT_PORT_MOCK, pactVersion = PactSpecVersion.V3)
//    void runTest() {
//        UserModel userModel = UserModel.builder()
//                .name(USERS[0][1])
//                .username(USERS[0][1])
//                .password(USERS[0][1])
//                .email(USERS[0][1])
//                .build();
//
//        Response response = getMockRequest(headers).body(userModel).post(URI_USERS);
//        assertEquals(201, response.getStatusCode());
//    }
//
//}
