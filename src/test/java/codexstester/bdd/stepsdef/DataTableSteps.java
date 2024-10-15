package codexstester.bdd.stepsdef;

import com.huntercodexs.javaspringbootcucumber.dto.Employee;
import com.huntercodexs.javaspringbootcucumber.dto.EmployeeAddress;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataTableSteps {

    /*
     * Example 1 - Using DataTableType
     * Use this format to map the values from a DataTable Feature File
    */
    @DataTableType
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

    /*
     * Example 2 - Using DataTableType
     * Use this format to map the values from a DataTable Feature File
     */
    @DataTableType
    public EmployeeAddress employeeAddressEntry(Map<String, String> entry) {
        return new EmployeeAddress(
                entry.get("id"),
                entry.get("street"),
                entry.get("city"),
                entry.get("number"),
                entry.get("state"));
    }

    private static String[] getValuesForObject(String[] rows, Field[] fields) {
        String[] cols = rows[0].replaceFirst("\\|", "").split("\\|");

        //Check data compatibility
        for (String col : cols) {
            boolean found = false;
            for (Field field : fields) {
                if (field.getName().equals(col.trim())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new RuntimeException("[Critical Error] Incompatible field: " + col.trim());
            }
        }
        return cols;
    }

    /**
     * <p>
     *     Example 3 - Using Reflection and Object
     *     Use this format to map the values from a DataTable Feature File
     * </p>
     *
     * <p>Expected  DataTable structure for input</p>
     *
     * <blockquote><pre>
     * DataTable table = List<Map<Object, Object>>
     * </pre></blockquote>
     */
    public <T> List<T> listMapToObject(DataTable table, Class<T> classT) {

        Field[] fields = classT.getDeclaredFields();
        String[] rows = table.toString().split("\\|\n");
        String[] cols = getValuesForObject(rows, fields);

        try {

            Object instanceClass = Class.forName(classT.getName()).getDeclaredConstructor().newInstance();

            List<T> objectList = new ArrayList<>();

            for (int i = 1; i < rows.length; i++) {

                String[] rowItems = rows[i].replaceFirst("\\|", "").split("\\|");

                //Fields
                for (int j = 0; j < rowItems.length; j++) {
                    Field field1 = classT.getDeclaredField(cols[j].trim());
                    field1.setAccessible(true);
                    field1.set(instanceClass, rowItems[j].trim());
                }

                objectList.add((T) instanceClass);
            }

            return objectList;

        } catch (
                IllegalAccessException |
                NoSuchFieldException |
                ClassNotFoundException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException e
        ) {
            throw new RuntimeException(e);
        }

    }

    @Given("user wants to create an employee using the following attributes")
    public void userWantsToCreateAnEmployeeUsingTheFollowingAttributes(DataTable employees) {
        System.out.println("========> " + employees);
    }

    @And("with the following address information")
    public void withTheFollowingAddressInformation(DataTable employeeAddresses) {
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
