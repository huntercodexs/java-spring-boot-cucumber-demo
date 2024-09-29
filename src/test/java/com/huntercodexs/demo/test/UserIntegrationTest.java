package com.huntercodexs.demo.test;

import com.huntercodexs.demo.client.DBClient;
import com.huntercodexs.demo.container.PostgresContainerSettings;
import com.huntercodexs.demo.model.UserModel;
import com.huntercodexs.demo.steps.context.UserContext;
import com.huntercodexs.demo.util.DatabaseUtil;
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

import static com.huntercodexs.demo.config.ConstantsConfig.*;
import static com.huntercodexs.demo.util.ResourceUtil.getRequestSpecification;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest extends PostgresContainerSettings {

    UserModel firstUser;
    UserModel secondUser;
    UserContext userContext;
    RequestSpecification request;
    DatabaseUtil databaseUtil;

    @LocalServerPort
    int port;

    @Autowired
    DBClient dbClient;

    static {
        postgres = new PostgreSQLContainer<>(DockerImageName
                .parse(DB_CONTAINER_NAME)
                .withTag(DB_CONTAINER_VERSION));
        postgres.start();
    }

    @BeforeAll
    void setUp() {
        Map<String, String> headers = new HashMap<>();
        request = getRequestSpecification()
                .baseUri(TARGET_PROTOCOL+"://"+TARGET_HOST+":" + port)
                .contentType(ContentType.JSON)
                .headers(headers);

        firstUser = createUser(0).as(UserModel.class);
        secondUser = createUser(1).as(UserModel.class);

        firstUser = new UserModel();
        secondUser = new UserModel();
        databaseUtil = new DatabaseUtil();

        userContext.setFirstUserDB(databaseUtil.findUserByUsername(USERS[0][0], USERS[0][1]));
        userContext.setSecondUserDB(databaseUtil.findUserByUsername(USERS[1][0], USERS[1][1]));
        userContext.setUserListDB(dbClient.getUsers());
    }

    @Test
    void testValidateFieldsForGetUsersEndpoint() {
        //Given I access the get users endpoint
        Response getUsersResponse = request.get(URI_USERS);

        //And I get a 200 successful response
        assertEquals(200, getUsersResponse.getStatusCode());

        //And The response has all the expected fields for the get users endpoint
        List<UserModel> userList = Arrays.asList(getUsersResponse.as(UserModel[].class));
        assertThat(userList).usingRecursiveComparison().isEqualTo(List.of(firstUser, secondUser));
    }

    @Test
    void testValidateFieldsForGetUsersEndpointAgainstDatabase() {
        //Given I access the users DB data
        userContext.setUserListDB(dbClient.getUsers());

        //Given I access the get users endpoint
        Response getUsersResponse = request.get(URI_USERS);

        //And I get a 200 successful response
        assertEquals(200, getUsersResponse.getStatusCode());

        //And I validate the response for the get users endpoint against the database
        assertThat(List.of(firstUser, secondUser))
                .usingRecursiveComparison()
                .ignoringFields(IGNORE_FIELDS)
                .isEqualTo(dbClient.getUsers());
    }

    private Response createUser(int userIndex) {
        UserModel user = UserModel.builder()
                .name(USERS[userIndex][0])
                .username(USERS[userIndex][1])
                .password(USERS[userIndex][2])
                .email(USERS[userIndex][3])
                .build();

        Response response = request.body(user).post(URI_USERS);
        assertEquals(201, response.getStatusCode());

        return response;
    }
}
