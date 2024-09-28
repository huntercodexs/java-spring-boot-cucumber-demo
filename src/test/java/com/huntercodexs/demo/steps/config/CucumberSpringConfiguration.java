package com.huntercodexs.demo.steps.config;

import com.huntercodexs.demo.container.PostgresContainerSettings;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static com.huntercodexs.demo.config.ConstantsConfig.ENV;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Profile(ENV)
@ActiveProfiles(ENV)
@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class, webEnvironment = RANDOM_PORT)
public class CucumberSpringConfiguration extends PostgresContainerSettings {

    @LocalServerPort
    private int port;

    @PostConstruct
    public void setup() {
        System.setProperty("port", String.valueOf(port));
    }

}
