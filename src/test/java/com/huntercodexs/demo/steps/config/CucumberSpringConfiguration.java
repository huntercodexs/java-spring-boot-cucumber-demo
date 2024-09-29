package com.huntercodexs.demo.steps.config;

import com.huntercodexs.demo.container.PostgresContainerSettings;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static com.huntercodexs.demo.config.ConstantsConfig.PROFILE;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Profile(PROFILE)
@ActiveProfiles(PROFILE)
@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class, webEnvironment = RANDOM_PORT)
public class CucumberSpringConfiguration extends PostgresContainerSettings {

    @LocalServerPort
    int port;

    @PostConstruct
    public void setup() {
        System.setProperty("port", String.valueOf(port));
    }

}
