package com.huntercodexs.cucumberdemo.employee.bdd;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberReportRunner.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
public class CucumberReportRunnerTest {
}
