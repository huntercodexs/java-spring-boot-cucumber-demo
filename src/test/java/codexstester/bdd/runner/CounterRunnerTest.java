package codexstester.bdd.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef",
        features = "src/test/resources/features/counter/Counter.feature",
        plugin = {"pretty", "html:target/cucumber-reports/counter-report.html"},
        tags = "@Counter"
)
public class CounterRunnerTest {
}
