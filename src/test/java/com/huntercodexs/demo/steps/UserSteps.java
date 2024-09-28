package com.huntercodexs.demo.steps;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.model.UserModel;
import com.huntercodexs.demo.steps.context.UserContext;
import io.cucumber.java.en.Then;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Data
@RequiredArgsConstructor
public class UserSteps {

    private final ApiSteps apiSteps;
    private final UserContext userContext;

    @Then("I validate the response for the get users endpoint against the database")
    public void iValidateResponseForGetUsersEndpointAgainstTheDatabase() {
        List<UserModel> userListApi = Arrays.asList(apiSteps.getResponse().as(UserModel[].class));
        List<UserEntity> userListDB = userContext.getUserListDB();

        for (UserEntity userDB : userListDB) {
            Optional<UserModel> matchingUser = findApiUserMatchingDB(userListApi, userDB);

            if (matchingUser.isPresent()) {
                UserModel userApi = matchingUser.get();

                assertThat(userApi)
                        .usingRecursiveComparison()
                        .ignoringFields("hometown", "hobbyAndInterests", "followerOf", "followedBy")
                        .isEqualTo(userDB);
            }
        }
    }

    private Optional<UserModel> findApiUserMatchingDB(List<UserModel> userListApi, UserEntity userDB) {
        return userListApi.stream().filter(userApi -> userApi.getId().equals(userDB.getId())).findFirst();
    }
}
