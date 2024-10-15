package codexstester.bdd.stepsdef;

import com.huntercodexs.javaspringbootcucumber.dto.Employee;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DataTableSteps {

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
    public void userWantsToCreateAnEmployeeUsingTheFollowingAttributes(DataTable table) {
        System.out.println("========> DataTable");
        List<Employee> objects = listMapToObject(table, Employee.class);
        System.out.println(objects);
    }

    @And("with the following address information")
    public void withTheFollowingAddressInformation(DataTable table) {
        System.out.println("========> " + table);
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
