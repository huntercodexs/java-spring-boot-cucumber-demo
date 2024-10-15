package codexstester.bdd.stepsdef.integration;

import com.huntercodexs.javaspringbootcucumber.JavaSpringBootCucumberApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@TestConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = JavaSpringBootCucumberApplication.class
)
public class CucumberSpringIntegration {
}
