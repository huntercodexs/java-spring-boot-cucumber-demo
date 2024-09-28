package com.huntercodexs.demo.util;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static com.huntercodexs.demo.config.ConstantsConfig.USERS;

public class DatabaseUtil {

    @Autowired
    UserRepository userRepository;

    public UserEntity findUserByUsername(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            userEntity = getUserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity = userRepository.save(userEntity);
        }

        return userEntity;
    }

    private UserEntity getUserEntity() {
        return UserEntity.builder()
                .name(USERS[0][1])
                .username(USERS[0][2])
                .password(USERS[0][3])
                .email(USERS[0][4])
                .build();
    }

}
