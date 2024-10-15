package codexstester.bdd.stepsdef;

import com.huntercodexs.javaspringbootcucumber.dto.Employee;
import com.huntercodexs.javaspringbootcucumber.dto.EmployeeAddress;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

public class DataTableSteps {

    public Employee employeeEntry(Map<String, String> entry) {
        return new Employee(
                entry.get("id"),
                entry.get("firstName"),
                entry.get("lastName"),
                entry.get("dateOfBirth"),
                entry.get("startDate"),
                entry.get("endDate"),
                entry.get("employmentType"),
                entry.get("email"));
    }

    public EmployeeAddress employeeAddressEntry(Map<String, String> entry) {
        return new EmployeeAddress(
                entry.get("id"),
                entry.get("street"),
                entry.get("city"),
                entry.get("number"),
                entry.get("state"));
    }

    @Given("user wants to create an employee using the following attributes")
    public void userWantsToCreateAnEmployeeUsingTheFollowingAttributes(List<Employee> employees) {
        System.out.println("========> " + employees);
    }

    @And("with the following address information")
    public void withTheFollowingAddressInformation(List<EmployeeAddress> employeeAddresses) {
        System.out.println("========> " + employeeAddresses);
    }

    @When("user try to save the new employee {string}")
    public void userTryToSaveTheNewEmployeeUsingAllRequiredFields(String condition) {
        System.out.println("========> " + condition);
    }

    @Then("the result is {string} and response is {string}")
    public void theResultIsSuccessfulAndResponseIs(String status, String response) {
        System.out.println("========> " + status);
        System.out.println("========> " + response);
    }

}
