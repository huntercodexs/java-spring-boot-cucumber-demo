package com.huntercodexs.cucumberdemo.employee.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "json:target/cucumber-report.json"},
        tags = "@Employee"
)
public class CucumberRunnerTest {
}
