package codexstester.bdd.stepsdef;

import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BackgroundDBSteps {

    @Given("the first user is added into database using username {string} and password {string}")
    public void addingFirstUserIntoDatabase(String username, String password) {
        System.out.println("=======> BackgroundDBSteps");
        System.out.println("=======> " + username);
        System.out.println("=======> " + password);
    }

    @Given("I add a second user to the DB with username {string} and password {string}")
    public void addingSecondUserIntoDatabase(String username, String password) {
        System.out.println("=======> " + username);
        System.out.println("=======> " + password);
    }

    @Given("database is accessed")
    public void databaseIsAccessed() {
        System.out.println("=======> GET DATA");
    }

    @Given("an authenticated user with username {string} and password {string} logs into the system")
    public void userAuthenticatedUsingUsernameAndPassword(String username, String password) {
        System.out.println("=======> " + username);
        System.out.println("=======> " + password);
    }

}
