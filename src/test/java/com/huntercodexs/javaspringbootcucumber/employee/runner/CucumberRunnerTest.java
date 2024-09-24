package com.huntercodexs.javaspringbootcucumber.employee.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@RunWith(CucumberReportRunner.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
public class CucumberRunnerTest {
}