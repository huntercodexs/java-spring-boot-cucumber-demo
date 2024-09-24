package com.huntercodexs.javaspringbootcucumber.employee.stepdefs;

import com.huntercodexs.javaspringbootcucumber.dto.AddressDTO;
import com.huntercodexs.javaspringbootcucumber.dto.EmployeeDTO;
import com.huntercodexs.javaspringbootcucumber.employee.client.RestAbstraction;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * <p>
 *     Tip: When we use the lambda expression for java8+ we do not need to use
 *     the method name s showed in this example class EmployeeSteps, we just
 *     create the constructor and put all steps inside it (Given, And, Then, etc...)
 * </p>
 */
public class EmployeeSteps extends RestAbstraction implements En {

    public EmployeeSteps() {

        //The first table called Employee
        Given("user wants to create an employee with the following attributes", (DataTable employeeDto) -> {
            testContext().reset();
            List<EmployeeDTO> employeeList = employeeDto.asList(EmployeeDTO.class);
            super.testContext().setPayload(employeeList.get(0)); //Get table fields name
        });

        //The second table called Address that have a relationship with the first table called Employee
        And("with the following address information", (DataTable addressDto) -> {
            List<AddressDTO> addressList = addressDto.asList(AddressDTO.class);
            super.testContext()
                    .getPayload(EmployeeDTO.class)
                    .setAddress(addressList);
        });

        //AbstractSteps class makes the POST call and stores response in TestContext
        When("user saves the new employee {string}", (String testContext) -> {
            String createEmployeeUrl = "/v1/employees";
            executePost(createEmployeeUrl);
        });

        //Step conclusion
        Then("the operation is {string} and the message is {string}", (String expectedResult, String expectedText) -> {
            Response response = testContext().getResponse();

            switch (expectedResult) {
                case "success":
                    assertThat(response.statusCode()).isIn(200, 201);
                    break;
                case "failure":
                    assertThat(response.statusCode()).isBetween(400, 504);
                    assertThat(response.getBody().print()).contains(expectedText);
                    break;
                default:
                    fail("Error: " + response.getBody().prettyPrint());
            }
        });
    }
}
