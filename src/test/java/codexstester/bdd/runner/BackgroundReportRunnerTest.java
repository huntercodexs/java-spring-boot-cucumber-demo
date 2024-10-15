package codexstester.bdd.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features/background")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "codexstester.bdd.stepsdef"
)
public class BackgroundReportRunnerTest {
}
