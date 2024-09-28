package com.huntercodexs.demo.pact.provider;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.VerificationReports;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import com.huntercodexs.demo.container.PostgresContainerSettings;
import com.huntercodexs.demo.model.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.huntercodexs.demo.config.ConstantsConfig.*;
import static com.huntercodexs.demo.util.ResourceUtil.getRequestSpecification;
import static com.huntercodexs.demo.util.ResourceUtil.logCurlFromPact;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@Provider(PACT_PROVIDER)
@PactFolder("target/pacts")
@PactBroker(host = PACT_URL_MOCK, consumers = {PACT_CONSUMER})
@VerificationReports(value = {"markdown"}, reportDir = "target/pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:reset.sql", "classpath:init.sql"}, executionPhase = BEFORE_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PactProviderIntegrationTest extends PostgresContainerSettings {

    @LocalServerPort
    int port;

    RequestSpecification rq;

    @BeforeAll
    void setUp() {
        Map<String, String> headers = new HashMap<>();

        rq = getRequestSpecification()
                .baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .auth()
                .basic(PACT_USERNAME, PACT_PASSWORD)
                .headers(headers);

        createUserResponseHelper(0);
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactTestTemplate(PactVerificationContext context, HttpRequest request) {
        String encoded = Base64.getEncoder()
                .encodeToString((PACT_USERNAME + ":" + PACT_PASSWORD).getBytes(StandardCharsets.UTF_8));

        request.addHeader("Authorization", "Basic " + encoded);
        logCurlFromPact(context, request, "http://localhost:" + port);
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port, ""));
    }

    @State("A request to retrieve a user")
    Map<String, Object> getUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", USERS[0][0]);
        return map;
    }

    @State("A request to update a user")
    Map<String, Object> updateUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", USERS[0][0]);
        return map;
    }

    private void createUserResponseHelper(int userIndex) {
        UserModel user = UserModel.builder()
                .name(USERS[userIndex][1])
                .username(USERS[userIndex][2])
                .password(USERS[userIndex][3])
                .email(USERS[userIndex][4])
                .build();

        Response response = rq.body(user).post(URI_USERS);
        assertEquals(201, response.getStatusCode());
    }
}
