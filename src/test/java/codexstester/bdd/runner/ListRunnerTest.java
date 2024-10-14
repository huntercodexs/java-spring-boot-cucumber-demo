package codexstester.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef",
        features = "src/test/resources/features/list/List.feature",
        plugin = {"pretty", "html:target/cucumber-reports/list-report.html"},
        tags = "@Things"
)
public class ListRunnerTest {
}
