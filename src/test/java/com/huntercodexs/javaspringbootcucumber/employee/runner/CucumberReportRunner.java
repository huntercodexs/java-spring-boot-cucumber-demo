package com.huntercodexs.javaspringbootcucumber.employee.runner;

import cucumber.api.junit.Cucumber;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

/**
 * This class is used to generate a Cucumber Report
 */
public class CucumberReportRunner extends Cucumber {

    private static final String PROJECT = "Java Spring Boot + Cucumber";
    private static final String VERSION = "1.0.0-SNAPSHOT";
    private static final String BRANCH = "master";

    public CucumberReportRunner(Class clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
        generateReport();
    }

    public static void generateReport() {
        File reportOutputDirectory = new File("target/classes/static");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-report.json");

        Configuration configuration = new Configuration(reportOutputDirectory, PROJECT);
        configuration.setBuildNumber(VERSION);
        configuration.addClassifications("Build Number", configuration.getBuildNumber());
        configuration.addClassifications("Branch Name", BRANCH);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
