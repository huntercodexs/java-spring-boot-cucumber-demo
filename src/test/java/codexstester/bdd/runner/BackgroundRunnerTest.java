package codexstester.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef",
        features = "src/test/resources/features/background/Background.feature",
        plugin = {"pretty", "html:target/cucumber-reports/background-report.html"},
        tags = "@BackgroundUser"
)
public class BackgroundRunnerTest {
}
