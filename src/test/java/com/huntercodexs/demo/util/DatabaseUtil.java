package com.huntercodexs.demo.util;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.huntercodexs.demo.config.ConstantsConfig.USERS;

@Component
@RequiredArgsConstructor
public class DatabaseUtil {

    @Autowired
    UserRepository userRepository;

    public UserEntity findUserByUsername(String username, String password, int userIndex) {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            userEntity = userRepository.save(getUserEntity(userIndex));
        }

        return userEntity;
    }

    private UserEntity getUserEntity(int userIndex) {
        return UserEntity.builder()
                .name(USERS[userIndex][1])
                .username(USERS[userIndex][2])
                .password(USERS[userIndex][3])
                .email(USERS[userIndex][4])
                .build();
    }

}
