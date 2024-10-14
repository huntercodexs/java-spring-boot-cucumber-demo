package codexstester.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef",
        features = "src/test/resources/features/datatable/DataTable.feature",
        plugin = {"pretty", "html:target/cucumber-reports/datatable-report.html"},
        tags = "@Employee and @EmployeeOK or @EmployeeNOK"
)
public class DataTableRunnerTest {
}
