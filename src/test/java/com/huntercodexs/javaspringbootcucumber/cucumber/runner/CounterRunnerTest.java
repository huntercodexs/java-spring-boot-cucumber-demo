package com.huntercodexs.javaspringbootcucumber.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/huntercodexs/javaspringbootcucumber/cucumber/feature/Counter.feature",
        glue = "com.huntercodexs.javaspringbootcucumber.cucumber.definition",
        plugin = {"pretty", "html:target/cucumber-reports/report.html"}
)
public class CounterRunnerTest {
}
