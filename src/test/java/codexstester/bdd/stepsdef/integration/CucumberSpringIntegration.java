package codexstester.bdd.stepsdef.integration;

import com.huntercodexs.javaspringbootcucumber.JavaSpringBootCucumberApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CucumberSpringIntegration {
}
