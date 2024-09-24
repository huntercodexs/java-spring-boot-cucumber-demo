package com.huntercodexs.javaspringbootcucumber.employee.context;

import com.huntercodexs.javaspringbootcucumber.Application;
import cucumber.api.java.Before;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
public class CucumberSpringContextConfiguration {

    @Before
    public void setUp() {
        System.out.println("Spring context is initializing for tests...");
    }

}
