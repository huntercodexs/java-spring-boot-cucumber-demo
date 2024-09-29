package com.huntercodexs.demo.steps;

import com.huntercodexs.demo.client.DBClient;
import com.huntercodexs.demo.steps.context.UserContext;
import com.huntercodexs.demo.util.DatabaseUtil;
import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DBSteps {

    private final DBClient dbClient;
    private final UserContext userContext;
    private final DatabaseUtil databaseUtil;

    @Given("the first user is added into database using username {string} and password {string}")
    public void addingFirstUserIntoDatabase(String username, String password) {
        userContext.setFirstUserDB(databaseUtil.findUserByUsername(username, password));
    }

    @Given("I add a second user to the DB with username {string} and password {string}")
    public void addingSecondUserIntoDatabase(String username, String password) {
        userContext.setSecondUserDB(databaseUtil.findUserByUsername(username, password));
    }

    @Given("database is accessed")
    public void databaseIsAceessed() {
        userContext.setUserListDB(dbClient.getUsers());
    }

    @Given("an authenticated user with username {string} and password {string} logs into the system")
    public void userAuthenticatedUsingUsernameAndPassword(String username, String password) {
        userContext.setFirstUserDB(databaseUtil.findUserByUsername(username, password));
    }

}
