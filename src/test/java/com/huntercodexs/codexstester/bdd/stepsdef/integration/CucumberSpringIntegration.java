package com.huntercodexs.codexstester.bdd.stepsdef.integration;

import com.huntercodexs.javaspringbootcucumber.JavaSpringBootCucumberApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = JavaSpringBootCucumberApplication.class
)
public class CucumberSpringIntegration {
}
