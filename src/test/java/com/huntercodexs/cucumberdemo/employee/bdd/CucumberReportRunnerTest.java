package com.huntercodexs.cucumberdemo.employee.bdd;

import com.huntercodexs.cucumberdemo.employee.bdd.report.CucumberReport;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberReport.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
public class CucumberReportRunnerTest {
}
