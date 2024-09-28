package com.huntercodexs.demo.steps;

import com.huntercodexs.demo.client.DBClient;
import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.repository.UserRepository;
import com.huntercodexs.demo.steps.context.UserContext;
import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DBSteps {

    private final DBClient dbClient;
    private final UserContext userContext;
    private final UserRepository userRepository;

    @Given("I add a user to the DB with username {string} and password {string}")
    public void iAddUserToDB(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            userEntity = getUserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity = userRepository.save(userEntity);
        }

        userContext.setUserDB(userEntity);
    }

    @Given("I add a second user to the DB with username {string} and password {string}")
    public void iAddSecondUserToDB(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            userEntity = getUserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity = userRepository.save(userEntity);
        }

        userContext.setSecondUserDB(userEntity);
    }

    @Given("I access the users DB data")
    public void iAccessUsersDBData() {
        userContext.setUserListDB(dbClient.getUsers());
    }

    @Given("an authenticated user with username {string} and password {string} logs into the system")
    public void userWithUsernameLogsIntoSystem(String username, String password) {
        // restClient.getRequestSpecification().and().auth().basic(username, password);
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            userEntity = getUserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
        }

        userContext.setUserDB(userEntity);
    }

    private UserEntity getUserEntity() {
        return UserEntity.builder()
                .name("anyName")
                .username("anyUsername")
                .password("anyPassword")
                .email("anyEmail")
                .build();
    }
}
