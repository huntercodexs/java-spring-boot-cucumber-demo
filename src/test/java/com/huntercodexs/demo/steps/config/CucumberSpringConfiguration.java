package com.huntercodexs.demo.steps.config;

import com.huntercodexs.demo.config.BasePostgresConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Profile("dev")
@ActiveProfiles("dev")
@SpringBootTest(classes = TestConfig.class, webEnvironment = RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberSpringConfiguration extends BasePostgresConfig {

    @LocalServerPort
    private int port;

    @PostConstruct
    public void setup() {
        System.setProperty("port", String.valueOf(port));
    }

}
