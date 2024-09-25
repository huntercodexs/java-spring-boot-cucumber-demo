package com.huntercodexs.cucumberdemo.employee.bdd.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.huntercodexs.cucumberdemo.employee.dto.AddressDTO;
import com.huntercodexs.cucumberdemo.employee.dto.EmployeeDTO;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import java.util.List;

public class EmployeeSteps extends AbstractSteps implements En {

    public EmployeeSteps() {

        Given("user wants to create an employee using the following attributes", (DataTable employeeDt) -> {
            testContext().reset();
            List<EmployeeDTO> employeeDTOList = employeeDt.asList(EmployeeDTO.class);
            super.testContext().setPayload(employeeDTOList.get(0));
        });

        And("with the following address information", (DataTable phoneDt) -> {
            List<AddressDTO> addressDTOList = phoneDt.asList(AddressDTO.class);
            super.testContext()
                    .getPayload(EmployeeDTO.class)
                    .setAddressDTOS(addressDTOList);
        });

        When("user try to save the new employee {string}", (String testContext) -> {
            String createEmployeeUrl = "/v1/employees";
            executePost(createEmployeeUrl);
        });

        Then("the result is {string} and response is {string}", (String expectedResult, String expectedResponse) -> {
            Response response = testContext().getResponse();

            switch (expectedResult) {
                case "successful":
                    assertThat(response.statusCode()).isIn(200, 201);
                    break;
                case "failure":
                    assertThat(response.statusCode()).isBetween(400, 504);
                    assertThat(response.getBody().print()).contains(expectedResponse);
                    break;
                default:
                    fail("Error: " + response.getBody().prettyPrint());
            }
        });
    }
}
