package com.huntercodexs.demo.steps.context;

import com.huntercodexs.demo.client.RestClient;
import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@ScenarioScope
public class ApiContext {
    private final RestClient restClient;
}
