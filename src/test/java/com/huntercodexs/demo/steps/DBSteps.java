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

    @Given("I add a first user to the DB with username {string} and password {string}")
    public void iAddUserToDB(String username, String password) {
        userContext.setFirstUserDB(databaseUtil.findUserByUsername(username, password));
    }

    @Given("I add a second user to the DB with username {string} and password {string}")
    public void iAddSecondUserToDB(String username, String password) {
        userContext.setSecondUserDB(databaseUtil.findUserByUsername(username, password));
    }

    @Given("I access the users DB data")
    public void iAccessUsersDBData() {
        userContext.setUserListDB(dbClient.getUsers());
    }

    @Given("an authenticated user with username {string} and password {string} logs into the system")
    public void userWithUsernameLogsIntoSystem(String username, String password) {
        userContext.setFirstUserDB(databaseUtil.findUserByUsername(username, password));
    }

}
