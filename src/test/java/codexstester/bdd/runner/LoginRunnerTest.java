package codexstester.bdd.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef",
        features = "src/test/resources/features/login/Login.feature",
        plugin = {"pretty", "html:target/cucumber-reports/login-report.html"},
        tags = "@Login"
)
public class LoginRunnerTest {
}
