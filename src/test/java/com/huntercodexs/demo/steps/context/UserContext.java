package com.huntercodexs.demo.steps.context;

import com.huntercodexs.demo.entity.UserEntity;
import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Getter
@Setter
@Component
@ScenarioScope
public class UserContext {
    private UserEntity firstUserDB;
    private UserEntity secondUserDB;
    private List<UserEntity> userListDB;
}
