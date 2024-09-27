package com.huntercodexs.demo.functionaltests;

import com.huntercodexs.demo.client.DBClient;
import com.huntercodexs.demo.config.BasePostgresConfig;
import com.huntercodexs.demo.model.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.huntercodexs.demo.utils.Utils.getRequestSpecification;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserIT extends BasePostgresConfig {

    @LocalServerPort
    int port;

    RequestSpecification rq;

    @Autowired
    DBClient dbClient;

    final static String USERNAME_1 = "john@email.com";
    final static String PASSWORD_1 = "1234";

    final static String USERNAME_2 = "mary@email.com";
    final static String PASSWORD_2 = "Password123!";
    UserModel user1;
    UserModel user2;

    static {
        postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres").withTag("latest"));
        postgres.start();
    }

    @BeforeAll
    void setUp() {
        Map<String, String> headers = new HashMap<>();
        rq = getRequestSpecification().baseUri("http://localhost:" + port)
                .contentType(ContentType.JSON)
                .headers(headers);

        user1 = createUser(USERNAME_1, PASSWORD_1).as(UserModel.class);
        user2 = createUser(USERNAME_2, PASSWORD_2).as(UserModel.class);
    }

    @Test
    void testValidateFieldsForGetUsersEndpoint() {
        // Given I access the get users endpoint
        Response getUsersResponse = rq.get("/api/v1/demo/users");

        // And I get a 200 successful response
        assertEquals(200, getUsersResponse.getStatusCode());

        // And The response has all the expected fields for the get users endpoint
        List<UserModel> userList = Arrays.asList(getUsersResponse.as(UserModel[].class));
        assertThat(userList).usingRecursiveComparison().isEqualTo(List.of(user1, user2));
    }

    @Test
    void testValidateFieldsForGetUsersEndpointAgainstDatabase() {
        // Given I access the get users endpoint
        Response getUsersResponse = rq.get("/api/v1/demo/users");

        // And I get a 200 successful response
        assertEquals(200, getUsersResponse.getStatusCode());

        // And I validate the response for the get users endpoint against the database
        assertThat(List.of(user1, user2)).usingRecursiveComparison()
                .ignoringFields("followerOf", "hometown", "hobbyAndInterests", "followedBy")
                .isEqualTo(dbClient.getUsers());
    }

    private Response createUser(String username, String password) {
        UserModel user = UserModel.builder()
                .name("anyName")
                .username(username)
                .password(password)
                .email("anyEmail")
                .build();

        Response response = rq.body(user).post("/api/v1/demo/users");
        assertEquals(201, response.getStatusCode());

        return response;
    }
}
