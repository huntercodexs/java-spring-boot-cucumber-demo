package com.huntercodexs.codexstester.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com.huntercodexs.codexstester.bdd.stepsdef",
        features = "src/test/resources/features/counter/Counter.feature",
        plugin = {"pretty", "html:target/cucumber-reports/counter-report.html"},
        tags = "@Counter"
)
public class CounterRunnerTest {
}
