package com.huntercodexs.demo.test;

import com.huntercodexs.demo.client.DBClient;
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

/**
 * User endpoint validation
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class UserIntegrationTest extends PostgresContainerSettings {
class UserIntegrationTest {

    UserModel firstUser;
    UserModel secondUser;
    RequestSpecification request;

    @LocalServerPort
    int port;

    @Autowired
    DBClient dbClient;

    @Autowired
    UserContext userContext;

    @Autowired
    DatabaseUtil databaseUtil;

//    static {
//        postgres = new PostgreSQLContainer<>(DockerImageName
//                .parse(DB_CONTAINER_NAME)
//                .withTag(DB_CONTAINER_VERSION));
//        postgres.start();
//    }

    /**
     * User setup in database
     */
    @BeforeAll
    void setUp() {

        //Given the first user is added into database using username "{username}" and password "{password}"
        databaseUtil.findUserByUsername(USERS[0][2], USERS[0][3]);
        //databaseUtil.findUserByUsername(USERS[1][2], USERS[1][3]);

        Map<String, String> headers = new HashMap<>();
        request = getRequestSpecification()
                .auth()
                .basic(USERS[0][2], USERS[0][3])
                .baseUri(TARGET_PROTOCOL+"://"+TARGET_HOST+":" + port)
                .contentType(ContentType.JSON)
                .headers(headers);
    }

    /**
     * First fields validation for get users endpoint
     */
    @Test
    void testValidateFieldsForGetUsersEndpoint() {
        //Given users endpoint is accessed using get
        Response getUsersResponse = request.get(URI_USERS);

        //And the result is 200 successful http response
        assertEquals(200, getUsersResponse.getStatusCode());

        //the http response has all expected fields for get request
        List<UserModel> userList = Arrays.asList(getUsersResponse.as(UserModel[].class));
        assertThat(userList).usingRecursiveComparison().isEqualTo(List.of(firstUser, secondUser));
    }

    /**
     * Second fields validation for get users endpoint
     */
    @Test
    void testValidateFieldsForGetUsersEndpointAgainstDatabase() {
        //Given database is accessed
        userContext.setUserListDB(dbClient.getUsers());

        //Given users endpoint is accessed using get
        Response getUsersResponse = request.get(URI_USERS);

        //And the result is 200 successful http response
        assertEquals(200, getUsersResponse.getStatusCode());

        //And the response is validated again from get users endpoint request
        assertThat(List.of(firstUser, secondUser))
                .usingRecursiveComparison()
                .ignoringFields(IGNORE_FIELDS)
                .isEqualTo(dbClient.getUsers());
    }

}
